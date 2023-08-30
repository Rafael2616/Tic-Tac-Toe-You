package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.presentation.game_screen.components.util.BaseButton

@Composable
fun ExpandButton() {
    val viewModel = viewModel<GameViewModel>()
    val isExpandButtonShown by viewModel.isExpandButtonShown.collectAsState()

    BaseButton(
        topPadding = 60.dp,
        startPadding = 15.dp,
        btnImage = if (isExpandButtonShown) R.drawable.expand_up_icon
    	else R.drawable.expand_down_icon,
        description = "Expand Button",
        onClickFun = { 
            viewModel.updateIsExpandButtonShown(!isExpandButtonShown)
        },
        btnHeight = 35.dp,
        transparentColor = true
    )
}