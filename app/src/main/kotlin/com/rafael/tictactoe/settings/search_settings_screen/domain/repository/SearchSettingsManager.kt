package com.rafael.tictactoe.settings.searchSettingsScreen.domain.repository

import android.app.Application
import androidx.appsearch.app.AppSearchSession
import androidx.appsearch.app.PutDocumentsRequest
import androidx.appsearch.app.SearchSpec
import androidx.appsearch.app.SetSchemaRequest
import androidx.appsearch.localstorage.LocalStorage
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchItemSpecs
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchSettingsManager @Inject constructor(
    private val appContext: Application,
) {
    private var session: AppSearchSession? = null

    suspend fun init() {
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

    suspend fun putSettings(settings: List<SearchItemSpecs>): Boolean {
        return withContext(Dispatchers.IO) {
            session?.putAsync(
                PutDocumentsRequest.Builder()
                    .addDocuments(settings)
                    .build(),
            )?.get()?.isSuccess == true
        }
    }

    suspend fun searchSettings(query: String): List<SearchItemSpecs> {
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

            val page = result.nextPageAsync.get()

            page.mapNotNull {
                if (it.genericDocument.schemaType == SearchItemSpecs::class.java.simpleName) {
                    it.getDocument(SearchItemSpecs::class.java)
                } else {
                    null
                }
            }
        }
    }

    fun closeSession() {
        session?.close()
        session = null
    }
}
