package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinatesTest
import com.rafael.tictactoe.game.domain.util.WinnerLineLogic
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun WinnerLine(coordinate: WinnerLineCoordinatesTest) {
    val viewModel = hiltViewModel<GameViewModel>()
    val startX by viewModel.startX.collectAsState()
    val startY by viewModel.startY.collectAsState()
    val endX by viewModel.endX.collectAsState()
    val endY by viewModel.endY.collectAsState()

	WinnerLineLogic(viewModel, coordinate)

	Column(
		modifier = Modifier
			.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Canvas(
			modifier = Modifier
            	.fillMaxSize()
           	 .weight(1f)
		) {
			drawLine(
				color = Color.Yellow,
                strokeWidth = 20.dp.toPx(),
                start = Offset(x = startX, y = startY),
				end = Offset(x = endX, y = endY)
			)
        }
	}
}