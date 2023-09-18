package com.rafael.tictactoe.game.domain.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun ToastImageHandler(): Painter {
    val viewModel = hiltViewModel<GameViewModel>()
    val play by viewModel.play.collectAsState()
    val isXFor1 by viewModel.isXFor1.collectAsState()
    val is1Win by viewModel.is1Win.collectAsState()
    val winner by viewModel.winner.collectAsState()
    val isXImage by viewModel.isXImage.collectAsState()
    val xImageMax = painterResource(R.drawable.x_image_max_size)
    val circleImageMax = painterResource(R.drawable.circle_image_max_size)
    var transparentImage = painterResource(R.drawable.transparent_image)
    var toastImage: Painter
    
    //Image showed when game not started yet (configuration fase)
	if (!play) {
        toastImage = if (isXFor1) xImageMax else circleImageMax
        
        return toastImage
	}

    //Image for case of game is on going	
    if (play && !winner) {
        toastImage = if (isXImage) xImageMax else circleImageMax
        
        return toastImage
    }
 
    //Image for case of has winner   
    if (winner) {
        toastImage = if (is1Win && isXFor1) {
            xImageMax
        } else if (is1Win && !isXFor1) { 
            circleImageMax
        } else if (!is1Win && isXFor1) {
            circleImageMax
        } else if (!is1Win && !isXFor1) {
            xImageMax
        } else {
            transparentImage
        }
        return toastImage
    }
    
    //Standard case, this shoudnt happen, returns transparent image
    return transparentImage
}