package com.rafael.tictactoe.game.presentation.game_screen.components.ui

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun WinnerLines() {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val winningPositionsIndices = viewModel.winningPositionsIndices.value
    val winnerLineColor by settingsVM.winnerLineColor.collectAsState()
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 8.dp, end = if (isInLandscape) 50.dp else 8.dp, bottom = if (isInLandscape) 0.dp else 50.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = if (isInLandscape) Alignment.End else Alignment.CenterHorizontally
    ) {
         Canvas(
             modifier = Modifier
             .fillMaxWidth(if (isInLandscape) 0.52f else 1f)
        ) {
            //Horizontal lines
            if (winningPositionsIndices.contains(0)) {
                drawWinnerLine(
                    start = Offset(x = 2.dp.toPx(), y = size.height - 330.dp.toPx()),
                    end = Offset(x = size.width - 2.dp.toPx(), y = size.height - 330.dp.toPx()),
                    color = winnerLineColor
                )
            }

            if (winningPositionsIndices.contains(1)) {
                drawWinnerLine(
                    start = Offset(x =  2.dp.toPx(), y = size.height - 198.dp.toPx()),
                    end = Offset(x = size.width - 2.dp.toPx(), y = size.height - 198.dp.toPx()),
                    color = winnerLineColor
                )
            }

            if (winningPositionsIndices.contains(2)) {
                drawWinnerLine(
                    start = Offset(x = 2.dp.toPx(), y = size.height - 67.dp.toPx()),
                    end = Offset(x = size.width - 2.dp.toPx(), y = size.height - 67.dp.toPx()),
                    color = winnerLineColor
                )
            }

            //Vertical lines
            if (winningPositionsIndices.contains(3)) {
                drawWinnerLine(
                    start = Offset(x = size.width / 6 + 1.dp.toPx(), y = size.height),
                    end = Offset(x = size.width / 6 + 1.dp.toPx(), y = size.height - 395.dp.toPx()),
                    color = winnerLineColor
                )
            }

            if (winningPositionsIndices.contains(4)) {
                drawWinnerLine(
                    start = Offset(x = (size.width / 2), y = size.height),
                    end = Offset(x = (size.width / 2), y = size.height - 395.dp.toPx()),
                    color = winnerLineColor
                )
            }

            if (winningPositionsIndices.contains(5)) {
                drawWinnerLine(
                    start = Offset(x = size.width - (size.width / 6) - 2.dp.toPx(), y = size.height),
                    end = Offset(x = size.width - (size.width / 6) - 2.dp.toPx(), y = size.height - 395.dp.toPx()),
                    color = winnerLineColor
                )
            }

            //Transversal lines
            if (winningPositionsIndices.contains(6)) {
                drawWinnerLine(
                    start = Offset(x = 10.dp.toPx(),  y = size.height - 386.dp.toPx()),
                    end = Offset(x = size.width - 10.dp.toPx(), y = size.height - 10.dp.toPx()),
                    color = winnerLineColor
                )
            }

            if (winningPositionsIndices.contains(7)) {
                drawWinnerLine(
                    start = Offset(x = size.width - 10.dp.toPx(), y = size.height - 386.dp.toPx()),
                    end = Offset(x = 10.dp.toPx(), y = size.height - 10.dp.toPx()),
                    color = winnerLineColor
                )
            }
        }
    }
}

fun DrawScope.drawWinnerLine(
    start: Offset,
    end: Offset,
    color: Color
) {
    drawLine(
        start = start,
        end = end,
        color = color,
        strokeWidth = 17.dp.toPx(),
    )
}