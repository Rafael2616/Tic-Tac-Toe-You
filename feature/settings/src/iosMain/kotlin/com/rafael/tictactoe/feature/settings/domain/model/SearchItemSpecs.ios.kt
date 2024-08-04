package com.rafael.tictactoe.feature.settings.domain.model

actual class SearchItemSpecs actual constructor(
    val _namespace: String,
    val _id: String,
    val _text: String,
) {
    actual val namespace: String = this._namespace
    actual val id: String = this._id
    actual val text: String = this._text
}
