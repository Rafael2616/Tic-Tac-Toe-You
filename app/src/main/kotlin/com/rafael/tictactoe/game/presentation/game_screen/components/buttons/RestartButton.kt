package com.rafael.tictactoe.game.presentation.game_screen.components.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.card_grid.EasyModeCPUCardClick
import com.rafael.tictactoe.game.domain.restart_game.RestartGame
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.presentation.game_screen.components.util.BaseButton

@Composable
fun RestartButton() {
    val viewModel = hiltViewModel<GameViewModel>()
    val isCPUMode by viewModel.isCPUMode.collectAsState()

    BaseButton(
        modifier = Modifier.padding(top = 55.dp, end = 30.dp),
        alignment = Alignment.TopEnd,
        btnImage = R.drawable.reset_icon,
        btnText = R.string.restart,
        description = "Restart Button",
        onClickFun = {
            viewModel.showExpandButton(false)
            RestartGame(viewModel)
            if (isCPUMode) {
                EasyModeCPUCardClick(viewModel)
            }
        }
    )
}