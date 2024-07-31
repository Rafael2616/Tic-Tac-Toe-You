package com.rafael.tictactoe.feature.settings.domain.model

import androidx.annotation.Keep
import androidx.appsearch.annotation.Document
import androidx.appsearch.annotation.Document.Id
import androidx.appsearch.annotation.Document.Namespace
import androidx.appsearch.annotation.Document.StringProperty
import androidx.appsearch.app.AppSearchSchema

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class SearchItemSpecs actual constructor(
    _namespace: String,
    _id: String,
    _text: String,
) {
    private val searchItemSpecs = AndroidSearchSettingsSpecs(
        namespace = _namespace,
        id = _id,
        text = _text,
    )

    actual val namespace: String
        get() = searchItemSpecs.namespace

    actual val id: String
        get() = searchItemSpecs.id

    actual val text: String
        get() = searchItemSpecs.text
}

@Keep
@Document
class AndroidSearchSettingsSpecs(
    @Namespace
    val namespace: String,
    @Id
    val id: String,
    @StringProperty(
        indexingType = AppSearchSchema.StringPropertyConfig.INDEXING_TYPE_PREFIXES,
    )
    val text: String,
)
