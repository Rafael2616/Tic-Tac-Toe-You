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
import com.rafael.tictactoe.game.presentation.utils.draws.drawCircleinToast
import com.rafael.tictactoe.game.presentation.utils.draws.drawXinToast
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun ToastImageHandler() {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val colorX by settingsVM.colorX.collectAsState()
    val colorCircle by settingsVM.colorCircle.collectAsState()
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
            if (isXFor1) drawXinToast(colorX) else drawCircleinToast(colorCircle)
    	}

        //Image for case of game is on going
        if (play && !winner) {
            if (isXImage) drawXinToast(colorX) else drawCircleinToast(colorCircle)
        }
 
        //Image for case of has winner   
        if (winner) {
            if (is1Win && isXFor1) {
                drawXinToast(colorX)
            } else if (is1Win && !isXFor1) { 
                drawCircleinToast(colorCircle)
            } else if (!is1Win && isXFor1) {
                drawCircleinToast(colorCircle)
            } else if (!is1Win && !isXFor1) {
                drawXinToast(colorX)
            }
        }
    }
}