package com.rafael.tictactoe.game.gameScreen.presentation.util.draws

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp

fun DrawScope.drawWinnerLineinColorDialog(color: Color) {
    drawLine(
        start = Offset(x = 28.dp.toPx(), y = 2.dp.toPx()),
        end = Offset(x = 2.dp.toPx(), y = 28.dp.toPx()),
        strokeWidth = 4.dp.toPx(),
        color = color,
    )
}
