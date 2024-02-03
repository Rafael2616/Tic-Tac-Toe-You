package com.rafael.tictactoe.game.gameScreen.presentation.components.ui

import android.content.res.Configuration
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState

@Composable
fun WinnerLines(
    state: GameState,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
) {
    val winningPositionsIndices = state.winningPositionsIndices
    val winnerLineColor = settingsState.winnerLineColor
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)
    val pPadding = PaddingValues(start = 8.dp, end = 8.dp, bottom = 50.dp)
    val lPadding = PaddingValues(start = 8.dp, end = 40.dp, bottom = 8.dp)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(if (isInLandscape) lPadding else pPadding),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = if (isInLandscape) Alignment.End else Alignment.CenterHorizontally,
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth(if (isInLandscape) 0.52f else 1f),
        ) {
            // Horizontal lines
            if (winningPositionsIndices.contains(0)) {
                drawWinnerLine(
                    start = Offset(x = 10.dp.toPx(), y = size.height - 330.dp.toPx()),
                    end = Offset(x = size.width - 10.dp.toPx(), y = size.height - 330.dp.toPx()),
                    color = winnerLineColor,
                )
            }

            if (winningPositionsIndices.contains(1)) {
                drawWinnerLine(
                    start = Offset(x = 10.dp.toPx(), y = size.height - 198.dp.toPx()),
                    end = Offset(x = size.width - 10.dp.toPx(), y = size.height - 198.dp.toPx()),
                    color = winnerLineColor,
                )
            }

            if (winningPositionsIndices.contains(2)) {
                drawWinnerLine(
                    start = Offset(x = 10.dp.toPx(), y = size.height - 67.dp.toPx()),
                    end = Offset(x = size.width - 10.dp.toPx(), y = size.height - 67.dp.toPx()),
                    color = winnerLineColor,
                )
            }

            // Vertical lines
            if (winningPositionsIndices.contains(3)) {
                drawWinnerLine(
                    start = Offset(x = size.width / 6 + 1.dp.toPx(), y = size.height - 8.dp.toPx()),
                    end = Offset(x = size.width / 6 + 1.dp.toPx(), y = size.height - 387.dp.toPx()),
                    color = winnerLineColor,
                )
            }

            if (winningPositionsIndices.contains(4)) {
                drawWinnerLine(
                    start = Offset(x = (size.width / 2), y = size.height - 8.dp.toPx()),
                    end = Offset(x = (size.width / 2), y = size.height - 387.dp.toPx()),
                    color = winnerLineColor,
                )
            }

            if (winningPositionsIndices.contains(5)) {
                drawWinnerLine(
                    start = Offset(x = size.width - (size.width / 6) - 2.dp.toPx(), y = size.height - 8.dp.toPx()),
                    end = Offset(x = size.width - (size.width / 6) - 2.dp.toPx(), y = size.height - 387.dp.toPx()),
                    color = winnerLineColor,
                )
            }

            // Transversal lines
            if (winningPositionsIndices.contains(6)) {
                drawWinnerLine(
                    start = Offset(x = 18.dp.toPx(), y = size.height - 378.dp.toPx()),
                    end = Offset(x = size.width - 18.dp.toPx(), y = size.height - 14.dp.toPx()),
                    color = winnerLineColor,
                )
            }

            if (winningPositionsIndices.contains(7)) {
                drawWinnerLine(
                    start = Offset(x = size.width - 18.dp.toPx(), y = size.height - 378.dp.toPx()),
                    end = Offset(x = 18.dp.toPx(), y = size.height - 14.dp.toPx()),
                    color = winnerLineColor,
                )
            }
        }
    }
}

fun DrawScope.drawWinnerLine(
    start: Offset,
    end: Offset,
    color: Color,
) {
    drawLine(
        start = start,
        end = end,
        color = color,
        strokeWidth = 17.dp.toPx(),
        cap = StrokeCap.Round,
    )
}
