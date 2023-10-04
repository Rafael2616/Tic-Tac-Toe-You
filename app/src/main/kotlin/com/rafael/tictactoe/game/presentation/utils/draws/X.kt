package com.rafael.tictactoe.game.presentation.utils.draws

import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

fun DrawScope.drawX (
    color: Color
) {
    drawLine(
        start = Offset(0f, 0f),
        end = Offset(60.dp.toPx(), 60.dp.toPx()),
        strokeWidth = 15.dp.toPx(),
        color = color
    )
    drawLine(
        start = Offset(0f, 60.dp.toPx()),
        end = Offset(60.dp.toPx(), 0f),
        strokeWidth = 15.dp.toPx(),
        color = color
    )
}