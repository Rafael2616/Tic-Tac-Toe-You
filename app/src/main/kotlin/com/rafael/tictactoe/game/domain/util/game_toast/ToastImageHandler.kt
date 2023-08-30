package com.rafael.tictactoe.game.domain.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun ToastImageHandler(): Painter {
    val viewModel = viewModel<GameViewModel>()
    val play by viewModel.play.collectAsState()
    val isXFor1 by viewModel.isXFor1.collectAsState()
    val winner by viewModel.winner.collectAsState()
    val isXImage by viewModel.isXImage.collectAsState()
    val xImageMax = painterResource(R.drawable.x_image_max_size)
    val circleImageMax = painterResource(R.drawable.circle_image_max_size)

	if(!play) {
	    viewModel.updateIsXImage(isXFor1)
	}
	
    val toastImage = if (!play) {
        if (isXFor1) xImageMax else circleImageMax
    } else {
        if (winner) {
            if (!isXImage) xImageMax else circleImageMax
        } else {
            if (isXImage) xImageMax else circleImageMax
        }
    }
    return toastImage
}