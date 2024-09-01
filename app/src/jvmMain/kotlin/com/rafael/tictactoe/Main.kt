package com.rafael.tictactoe

import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import com.rafael.tictactoe.core.di.initKoinJvm
import com.rafael.tictactoe.core.navigation.impl.TicTacToeNavGraph
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.app_name
import java.awt.Dimension
import org.jetbrains.compose.resources.stringResource

fun main() = application {
    remember { initKoinJvm() }
    Window(
        title = stringResource(Res.string.app_name),
        state = rememberWindowState(
            width = 420.dp,
            height = 780.dp,
            position = WindowPosition.Aligned(Alignment.Center),
        ),
        onCloseRequest = ::exitApplication,
        icon = painterResource("ic_launcher_foreground.png"),
    ) {
        window.minimumSize = Dimension(400, 750)
        TicTacToeNavGraph()
    }
}
