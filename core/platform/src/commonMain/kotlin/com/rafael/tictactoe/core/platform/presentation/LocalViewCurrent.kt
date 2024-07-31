package com.rafael.tictactoe.core.platform.presentation

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class LocalViewCurrent {
    companion object {
        val height: Int
        val width: Int
    }
}
