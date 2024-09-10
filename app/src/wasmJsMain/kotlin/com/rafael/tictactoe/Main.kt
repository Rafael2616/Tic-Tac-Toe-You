package com.rafael.tictactoe

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.rafael.tictactoe.core.di.initKoinWasmJs
import com.rafael.tictactoe.core.navigation.impl.TicTacToeNavGraph
import kotlinx.browser.document

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    initKoinWasmJs()
    ComposeViewport(document.body!!) {
        TicTacToeNavGraph()
    }
}
