package com.rafael.tictactoe.game.gameScreen.presentation.util.draws

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

fun DrawScope.drawAnCircle(
    color: Color,
) {
    drawCircle(
        center = Offset(47.dp.toPx(), 47.dp.toPx()),
        radius = 34.dp.toPx(),
        style = Stroke(width = 15.dp.toPx()),
        color = color,
    )
}
