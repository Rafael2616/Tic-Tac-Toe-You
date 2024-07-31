package com.rafael.tictactoe.feature.settingssearch.data.repository

import android.app.Application
import androidx.appsearch.app.AppSearchSession
import androidx.appsearch.app.PutDocumentsRequest
import androidx.appsearch.app.SearchSpec
import androidx.appsearch.app.SetSchemaRequest
import androidx.appsearch.localstorage.LocalStorage
import com.rafael.tictactoe.feature.settings.domain.model.AndroidSearchSettingsSpecs
import com.rafael.tictactoe.feature.settings.domain.model.SearchItemSpecs
import com.rafael.tictactoe.feature.settingssearch.domain.repository.SearchSettingsManager
import java.util.concurrent.ExecutionException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AndroidSearchSettingsManager(
    private val appContext: Application,
) : SearchSettingsManager {
    private var session: AppSearchSession? = null

    override suspend fun init() {
        withContext(Dispatchers.IO) {
            val sessionFuture = LocalStorage.createSearchSessionAsync(
                LocalStorage.SearchContext.Builder(
                    appContext,
                    "setting",
                ).build(),
            )
            val setSchemaRequest = SetSchemaRequest.Builder()
                .addDocumentClasses(AndroidSearchSettingsSpecs::class.java)
                .build()

            session = sessionFuture.get()

            session?.setSchemaAsync(setSchemaRequest)
        }
    }

    override suspend fun putSettings(settings: List<SearchItemSpecs>): Boolean {
        val androidSettings = settings.map {
            AndroidSearchSettingsSpecs(
                namespace = it.namespace,
                id = it.id,
                text = it.text,
            )
        }
        return withContext(Dispatchers.IO) {
            session?.putAsync(
                PutDocumentsRequest.Builder()
                    .addDocuments(androidSettings)
                    .build(),
            )?.get()?.isSuccess == true
        }
    }

    override suspend fun searchSettings(query: String): List<SearchItemSpecs> {
        return withContext(Dispatchers.IO) {
            val searchSpec = SearchSpec.Builder()
                .setSnippetCount(50)
                .addFilterNamespaces("settings")
                .setRankingStrategy(SearchSpec.RANKING_STRATEGY_USAGE_COUNT)
                .build()
            val result = session?.search(
                query,
                searchSpec,
            ) ?: return@withContext emptyList()

            val page = try {
                result.nextPageAsync.get()
            } catch (e: ExecutionException) {
                e.printStackTrace()
                null
            }

            page?.mapNotNull {
                if (it.genericDocument.schemaType == AndroidSearchSettingsSpecs::class.java.simpleName) {
                    val specs = it.getDocument(AndroidSearchSettingsSpecs::class.java)
                    SearchItemSpecs(
                        _namespace = specs.namespace,
                        _id = specs.id,
                        _text = specs.text,
                    )
                } else {
                    null
                }
            } ?: emptyList()
        }
    }

    override fun closeSession() {
        session?.close()
        session = null
    }
}
