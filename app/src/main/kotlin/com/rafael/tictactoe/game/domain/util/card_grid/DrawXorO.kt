package com.rafael.tictactoe.game.domain.util

import androidx.compose.ui.graphics.drawscope.DrawScope
import com.rafael.tictactoe.game.presentation.utils.draws.drawX
import com.rafael.tictactoe.game.presentation.utils.draws.drawO
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun DrawScope.drawXorO(
    rowIndex: Int,
    cardIndex: Int,
    isCardClicked: List<List<Boolean>>,
    card: List<List<Boolean>>,
    viewModel: SettingsViewModel
) {
    val colorX = viewModel.colorX.value
    val colorO = viewModel.colorO.value

    if (isCardClicked[rowIndex][cardIndex]) {
    	if (card[rowIndex][cardIndex]) {
            drawX(colorX)
        } else {
            drawO(colorO)
        }
	}
}