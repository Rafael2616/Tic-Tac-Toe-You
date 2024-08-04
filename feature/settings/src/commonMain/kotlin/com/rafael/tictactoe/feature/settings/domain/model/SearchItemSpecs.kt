package com.rafael.tictactoe.feature.settings.domain.model

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class SearchItemSpecs(_namespace: String, _id: String, _text: String) {
    val namespace: String
    val id: String
    val text: String
}
