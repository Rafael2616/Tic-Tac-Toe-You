package com.rafael.tictactoe.feature.settings.domain.model

expect class SearchItemSpecs(_namespace: String, _id: String, _text: String) {
    val namespace: String
    val id: String
    val text: String
}
