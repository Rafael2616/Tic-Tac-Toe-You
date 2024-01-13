package com.rafael.tictactoe.settings.settingsScreen.domain.model

import androidx.appsearch.annotation.Document
import androidx.appsearch.annotation.Document.Id
import androidx.appsearch.annotation.Document.Namespace
import androidx.appsearch.annotation.Document.StringProperty
import androidx.appsearch.app.AppSearchSchema

@Document
data class SettingItemDoc(
    @Namespace
    val namespace: String,
    @Id
    val id: String,
    @StringProperty(
        indexingType = AppSearchSchema.StringPropertyConfig.INDEXING_TYPE_PREFIXES,
    )
    val text: String,
)
