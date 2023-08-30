package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.util.VictoryCounterReseter
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.presentation.game_screen.components.util.BaseButton

@Composable
fun VictoryCounterReseterButton() {
    val viewModel = viewModel<GameViewModel>()

    BaseButton(
        topPadding = 55.dp,
        startPadding = 65.dp,
        btnImage = R.drawable.trash_icon,
        btnText = R.string.reset,
        description = "Victory Counter Reseter Button",
        onClickFun = { 
            VictoryCounterReseter(viewModel)
        }
    )
}