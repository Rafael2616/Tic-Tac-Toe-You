package com.rafael.tictactoe.feature.settingssearch.data.repository

import android.app.Application
import androidx.appsearch.app.AppSearchSession
import androidx.appsearch.app.PutDocumentsRequest
import androidx.appsearch.app.SearchSpec
import androidx.appsearch.app.SetSchemaRequest
import androidx.appsearch.localstorage.LocalStorage
import com.rafael.tictactoe.feature.settings.domain.model.SearchItemSpecs
import java.util.concurrent.ExecutionException
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AndroidSearchSettingsManager @Inject constructor(
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
                .addDocumentClasses(SearchItemSpecs::class.java)
                .build()

            session = sessionFuture.get()

            session?.setSchemaAsync(setSchemaRequest)
        }
    }

    override suspend fun putSettings(settings: List<SearchItemSpecs>): Boolean {
        return withContext(Dispatchers.IO) {
            session?.putAsync(
                PutDocumentsRequest.Builder()
                    .addDocuments(settings)
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
                if (it.genericDocument.schemaType == SearchItemSpecs::class.java.simpleName) {
                    it.getDocument(SearchItemSpecs::class.java)
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
