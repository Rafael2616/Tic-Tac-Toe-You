import sqlite3InitModule from '@sqlite.org/sqlite-wasm';

let sqlite3 = null;
const databases = new Map();
const statements = new Map();
let nextDatabaseId = 0;
let nextStatementId = 0;

function openRequest(id, requestData) {
  try {
    const newDatabaseId = nextDatabaseId++;
    let newDatabase;
    if (typeof sqlite3?.oo1?.OpfsDb === 'function') {
      try {
        newDatabase = new sqlite3.oo1.OpfsDb(requestData.fileName);
      } catch (opfsError) {
        console.warn("Failed to instantiate OpfsDb, falling back to sqlite3.oo1.DB:", opfsError);
        newDatabase = new sqlite3.oo1.DB(requestData.fileName, 'c');
      }
    } else if (typeof sqlite3?.oo1?.DB === 'function') {
      console.warn("sqlite3.oo1.OpfsDb is not available. Falling back to sqlite3.oo1.DB.");
      newDatabase = new sqlite3.oo1.DB(requestData.fileName, 'c');
    } else {
      throw new Error("SQLite OO1 database constructors are not available.");
    }
    databases.set(newDatabaseId, newDatabase);
    postMessage({'id': id, data: {'databaseId': newDatabaseId}});
  } catch (error) {
    postMessage({'id': id, error: error.message});
  }
}

function prepareRequest(id, requestData) {
  try {
    const newStatementId = nextStatementId++;
    const resultData = { 'statementId': newStatementId, 'parameterCount': 0, 'columnNames': [] };
    const database = databases.get(requestData.databaseId);
    if (!database) { postMessage({'id': id, error: "Invalid database ID: " + requestData.databaseId}); return; }
    const statement = database.prepare(requestData.sql);
    statements.set(newStatementId, statement);
    resultData.parameterCount = sqlite3.capi.sqlite3_bind_parameter_count(statement);
    for (let i = 0; i < statement.columnCount; i++) { resultData.columnNames.push(sqlite3.capi.sqlite3_column_name(statement, i)); }
    postMessage({'id': id, data: resultData});
  } catch (error) { postMessage({'id': id, error: error.message}); }
}

function stepRequest(id, requestData) {
  const statement = statements.get(requestData.statementId);
  if (!statement) { postMessage({'id': id, error: "Invalid statement ID: " + requestData.statementId}); return; }
  try {
    const resultData = { 'rows': [], 'columnTypes': [] };
    statement.reset();
    statement.clearBindings();
    for (let i = 0; i < requestData.bindings.length; i++) { statement.bind(i + 1, requestData.bindings[i]); }
    while (statement.step()) {
      if (!resultData.columnTypes.length) {
        for (let i = 0; i < statement.columnCount; i++) { resultData.columnTypes.push(sqlite3.capi.sqlite3_column_type(statement, i)); }
      }
      resultData.rows.push(statement.get([]));
    }
    postMessage({'id': id, data: resultData});
  } catch (error) { postMessage({'id': id, error: error.message}); }
}

function closeRequest(id, requestData) {
  if (requestData.statementId) {
    const statement = statements.get(requestData.statementId);
    if (statement) { try { statement.finalize(); statements.delete(requestData.statementId); } catch (e) {} }
  }
  if (requestData.databaseId) {
    const database = databases.get(requestData.databaseId);
    if (database) { try { database.close(); databases.delete(requestData.databaseId); } catch (e) {} }
  }
}

const commandMap = { 'open': openRequest, 'prepare': prepareRequest, 'step': stepRequest, 'close': closeRequest };

function handleMessage(e) {
  const requestMsg = e.data;
  if (!requestMsg || !requestMsg.data || !requestMsg.data.cmd) return;
  const command = requestMsg.data.cmd;
  const requestHandler = commandMap[command];
  if (requestHandler) { requestHandler(requestMsg.id, requestMsg.data); }
}

const messageQueue = [];
onmessage = (e) => {
  if (!sqlite3) { messageQueue.push(e); } else { handleMessage(e); }
};

sqlite3InitModule().then(instance => {
  sqlite3 = instance;
  while (messageQueue.length > 0) { handleMessage(messageQueue.shift()); }
});
