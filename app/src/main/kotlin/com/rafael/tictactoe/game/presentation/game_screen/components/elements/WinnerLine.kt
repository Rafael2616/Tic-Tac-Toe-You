package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun WinnerLine() {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val winningPositionsIndices = viewModel.winningPositionsIndices.value
    val winnerLineColor by settingsVM.winnerLineColor.collectAsState()
 
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, bottom = 45.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
         Canvas(
             modifier = Modifier.fillMaxWidth(),
        ) {
            //Horizontal lines
            if (winningPositionsIndices.contains(0)) {    
                drawWinnerLine(
                    start = Offset(x = 2.dp.toPx(), y = size.height - 328.dp.toPx()),
                    end = Offset(x = size.width - 2.dp.toPx(), y = size.height - 328.dp.toPx()),
                    color = winnerLineColor
                )
            }

            if (winningPositionsIndices.contains(1)) {
                drawWinnerLine(
                    start = Offset(x =  2.dp.toPx(), y = size.height - 197.dp.toPx()),
                    end = Offset(x = size.width - 2.dp.toPx(), y = size.height - 197.dp.toPx()),
                    color = winnerLineColor
                )
            }

            if (winningPositionsIndices.contains(2)) {
                drawWinnerLine(
                    start = Offset(x = 2.dp.toPx(), y = size.height - 65.dp.toPx()),
                    end = Offset(x = size.width - 2.dp.toPx(), y = size.height - 65.dp.toPx()),
                    color = winnerLineColor
                )
            }

            //Vertical lines
            if (winningPositionsIndices.contains(3)) {
                drawWinnerLine(
                    start = Offset(x = size.width / 6, y = size.height),
                    end = Offset(x = size.width / 6, y = size.height - 395.dp.toPx()),
                    color = winnerLineColor
                )
            }

            if (winningPositionsIndices.contains(4)) {
                drawWinnerLine(
                    start = Offset(x = (size.width / 2) - 1.5.dp.toPx(), y = size.height),
                    end = Offset(x = (size.width / 2) - 1.5.dp.toPx(), y = size.height - 395.dp.toPx()),
                    color = winnerLineColor
                )
            }

            if (winningPositionsIndices.contains(5)) {
                drawWinnerLine(
                    start = Offset(x = size.width - (size.width / 6) - 3.dp.toPx(), y = size.height),
                    end = Offset(x = size.width - (size.width / 6) - 3.dp.toPx(), y = size.height - 395.dp.toPx()),
                    color = winnerLineColor
                )
            }

            //Transversal lines
            if (winningPositionsIndices.contains(6)) {
                drawWinnerLine(
                    start = Offset(x = 2.dp.toPx(),  y = size.height - 395.dp.toPx()),
                    end = Offset(x = size.width - 2.dp.toPx(), y = size.height),
                    color = winnerLineColor
                )
            }
                
            if (winningPositionsIndices.contains(7)) {
                drawWinnerLine(
                    start = Offset(x = size.width - 2.dp.toPx(), y = size.height - 395.dp.toPx()),
                    end = Offset(x = 2.dp.toPx(), y = size.height),
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
        strokeWidth = 18.dp.toPx(),
    )
}