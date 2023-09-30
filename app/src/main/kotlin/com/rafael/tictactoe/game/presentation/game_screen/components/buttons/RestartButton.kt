package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.util.RandomCardSorter
import com.rafael.tictactoe.game.domain.util.RestartGame
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.presentation.game_screen.components.util.BaseButton
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun RestartButton() {
    val viewModel = hiltViewModel<GameViewModel>()
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val context = LocalContext.current
    val isCPUPlayer1 by viewModel.isCPUPlayer1.collectAsState()
    val isCPUMode by viewModel.isCPUMode.collectAsState()
    val is1Win by viewModel.is1Win.collectAsState()

    BaseButton(
        modifier = Modifier.padding(top = 55.dp, end = 30.dp),
        alignment = Alignment.TopEnd,
        btnImage = R.drawable.reset_icon,
        btnText = R.string.restart,
        description = "Restart Button",
        onClickFun = {
            viewModel.updateIsExpandButtonShown(false)
            RestartGame(viewModel)
            if (isCPUMode) {
                if (isCPUPlayer1 && is1Win || !isCPUPlayer1 && !is1Win) {
                    RandomCardSorter(viewModel, settingsVM, context)
                }
            }
        }
    )
}