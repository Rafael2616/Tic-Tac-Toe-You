package com.rafael.tictactoe.game.domain.card_grid

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.game.presentation.utils.draws.drawX
import com.rafael.tictactoe.game.presentation.utils.draws.drawAnCircle
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun DrawXorCircle(
    rowIndex: Int,
    cardIndex: Int
) {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val viewModel = hiltViewModel<GameViewModel>()
    val card by viewModel.card.collectAsState()
	val isCardClicked by viewModel.isCardClicked.collectAsState()
    val xColor by settingsVM.xColor.collectAsState()
    val circleColor by settingsVM.circleColor.collectAsState()

    AnimatedVisibility(
        visible = isCardClicked[rowIndex][cardIndex],
        enter = fadeIn() + expandVertically(),
        exit = fadeOut() + shrinkVertically()
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
        	if (card[rowIndex][cardIndex]) {
                drawX(xColor)
            } else {
                drawAnCircle(circleColor)
            }
        }
    }
}