package com.rafael.tictactoe.game.domain.util

import androidx.compose.ui.graphics.drawscope.DrawScope
import com.rafael.tictactoe.game.presentation.utils.draws.drawX
import com.rafael.tictactoe.game.presentation.utils.draws.drawAnCircle
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun DrawScope.drawXorCircle(
    rowIndex: Int,
    cardIndex: Int,
    isCardClicked: List<List<Boolean>>,
    card: List<List<Boolean>>,
    viewModel: SettingsViewModel
) {
    val colorX = viewModel.colorX.value
    val colorCircle = viewModel.colorCircle.value

    if (isCardClicked[rowIndex][cardIndex]) {
    	if (card[rowIndex][cardIndex]) {
            drawX(colorX)
        } else {
            drawAnCircle(colorCircle)
        }
	}
}