package com.rafael.tictactoe.game.presentation.utils.draws

import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp

fun DrawScope.drawO(
    color: Color
) {
    drawCircle(
        center = Offset(58.dp.toPx(), 58.dp.toPx()),
        radius = 38.dp.toPx(),
        style = Stroke(width = 15.dp.toPx()),
        color = color
    )
}