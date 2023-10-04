package com.rafael.tictactoe.game.domain.util

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.presentation.utils.draws.drawOPreview
import com.rafael.tictactoe.game.presentation.utils.draws.drawXPreview
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun DrawScope.drawO(colorO: Color) = drawOPreview(
    center = Offset(8.dp.toPx(), 8.dp.toPx()),
    radius = 8.dp,
    stroke = Stroke(width = 3.dp.toPx()),
    colorO = colorO
)
fun DrawScope.drawX(colorX: Color) = drawXPreview(
    strokeWidth = 3.dp,
    start1 = Offset(x = 0f, y = 0f),
    end1 = Offset(x = 15.dp.toPx(), y = 15.dp.toPx()),
    start2 = Offset(x = 15.dp.toPx(), y = 0f),
    end2 = Offset(x = 0.dp.toPx(), y = 15.dp.toPx()),
    colorX = colorX
)

@Composable
fun ToastImageHandler() {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val colorX by settingsVM.colorX.collectAsState()
    val colorO by settingsVM.colorO.collectAsState()
    val play by viewModel.play.collectAsState()
    val isXFor1 by viewModel.isXFor1.collectAsState()
    val is1Win by viewModel.is1Win.collectAsState()
    val winner by viewModel.winner.collectAsState()
    val isXImage by viewModel.isXImage.collectAsState()

    Canvas(
         modifier = Modifier
         .fillMaxSize()
    ) {
        //Image showed when game not started yet (configuration fase)
    	if (!play) {
            if (isXFor1) drawX(colorX) else drawO(colorO)
    	}

        //Image for case of game is on going
        if (play && !winner) {
            if (isXImage) drawX(colorX) else drawO(colorO)
        }
 
        //Image for case of has winner   
        if (winner) {
            if (is1Win && isXFor1) {
                drawX(colorX)
            } else if (is1Win && !isXFor1) { 
                drawO(colorO)
            } else if (!is1Win && isXFor1) {
                drawO(colorO)
            } else if (!is1Win && !isXFor1) {
                drawX(colorX)
            }
        }
    }
}