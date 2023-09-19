package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.util.RandomCardSorter
import com.rafael.tictactoe.game.domain.util.RestartGame
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.presentation.game_screen.components.util.BaseButton

@Composable
fun RestartButton() {
    val viewModel = hiltViewModel<GameViewModel>()

    BaseButton(
        topPadding = 55.dp,
        endPadding = 30.dp,
        alignment = Alignment.TopEnd,
        btnImage = R.drawable.reset_icon,
        btnText = R.string.restart,
        description = "Restart Button",
        onClickFun = {
            viewModel.updateIsExpandButtonShown(false)
            RestartGame(viewModel)
        }
    )
}