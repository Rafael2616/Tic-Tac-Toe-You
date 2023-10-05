package com.rafael.tictactoe.game.presentation.utils.draws

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

//X Image adjusted for ToastImageHandler
fun DrawScope.drawXinToast(colorX: Color) = drawXPreview(
    strokeWidth = 3.dp,
    start1 = Offset(x = 0.dp.toPx(), y = 0.dp.toPx()),
    end1 = Offset(x = 15.dp.toPx(), y = 15.dp.toPx()),
    start2 = Offset(x = 15.dp.toPx(), y = 0f),
    end2 = Offset(x = 0.dp.toPx(), y = 15.dp.toPx()),
    colorX = colorX
)

//X Image adjusted for VictoryCounter
fun DrawScope.drawXinCounter(colorX: Color) = drawXPreview(
    strokeWidth = 3.dp,
    start1 = Offset(x = 27.dp.toPx(), y = 3.dp.toPx()),
    end1 = Offset(x = 40.dp.toPx(), y = 16.dp.toPx()),
    start2 = Offset(x = 40.dp.toPx(), y = 3.dp.toPx()),
    end2 = Offset(x = 27.dp.toPx(), y = 16.dp.toPx()),
    colorX = colorX
)