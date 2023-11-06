package com.rafael.tictactoe.game.presentation.utils.draws

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

fun DrawScope.drawWinnerLineinColorDialog(color: Color) {
    drawLine(
        start = Offset(x = 20.dp.toPx(), y = 0.dp.toPx()),
        end = Offset(x = 0.dp.toPx(), y = 20.dp.toPx()),
        strokeWidth = 4.dp.toPx(),
        color = color
    )
}