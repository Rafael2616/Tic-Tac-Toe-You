package com.rafael.tictactoe.game.presentation.utils.draws

import androidx.compose.foundation.Canvas
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp

fun DrawScope.drawXPreview (
    strokeWidth: Dp,
    start1: Offset,
    start2: Offset,
    end1: Offset,
    end2: Offset,
    colorX: Color
) {
    drawLine(
        start = start1,
        end = end1,
        strokeWidth = strokeWidth.toPx(),
        color = colorX
    )
    drawLine(
        start = start2,
        end = end2,
        strokeWidth = strokeWidth.toPx(),
        color = colorX
    )
}