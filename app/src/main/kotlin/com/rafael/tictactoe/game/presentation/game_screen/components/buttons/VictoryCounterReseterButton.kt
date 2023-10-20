package com.rafael.tictactoe.game.presentation.game_screen.components.buttons

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.presentation.game_screen.components.util.BaseButton

@Composable
fun VictoryCounterReseterButton() {
    val viewModel = hiltViewModel<GameViewModel>()

    BaseButton(
        modifier = Modifier.padding(top = 55.dp, start = 65.dp),
        btnImage = R.drawable.trash_icon,
        btnText = R.string.reset,
        description = "Victory Counter Reseter Button",
        onClickFun = {
            viewModel.showVictoryCounterReseter(false)
            viewModel.resetVictoryCounter()
        }
    )
}