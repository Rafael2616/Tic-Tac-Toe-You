package com.rafael.tictactoe.game.presentation.utils.draws

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

fun DrawScope.drawX(
    color: Color,
) {
    drawLine(
        start = Offset(15.dp.toPx(), 15.dp.toPx()),
        end = Offset(79.dp.toPx(), 79.dp.toPx()),
        strokeWidth = 15.dp.toPx(),
        color = color,
    )
    drawLine(
        start = Offset(15.dp.toPx(), 79.dp.toPx()),
        end = Offset(79.dp.toPx(), 15.dp.toPx()),
        strokeWidth = 15.dp.toPx(),
        color = color,
    )
}
