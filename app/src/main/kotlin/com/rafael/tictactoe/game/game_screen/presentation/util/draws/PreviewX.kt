package com.rafael.tictactoe.game.gameScreen.presentation.util.draws

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun DrawScope.drawXPreview(
    strokeWidth: Dp,
    start1: Offset,
    start2: Offset,
    end1: Offset,
    end2: Offset,
    colorX: Color,
) {
    drawLine(
        start = start1,
        end = end1,
        strokeWidth = strokeWidth.toPx(),
        color = colorX,
    )
    drawLine(
        start = start2,
        end = end2,
        strokeWidth = strokeWidth.toPx(),
        color = colorX,
    )
}

// X Image adjusted for ToastImageHandler
fun DrawScope.drawXinToast(colorX: Color) = drawXPreview(
    strokeWidth = 3.dp,
    start1 = Offset(x = 0.dp.toPx(), y = 0.dp.toPx()),
    end1 = Offset(x = 15.dp.toPx(), y = 15.dp.toPx()),
    start2 = Offset(x = 15.dp.toPx(), y = 0f),
    end2 = Offset(x = 0.dp.toPx(), y = 15.dp.toPx()),
    colorX = colorX,
)

// X Image adjusted for VictoryCounter
fun DrawScope.drawXinCounter(colorX: Color) = drawXPreview(
    strokeWidth = 3.dp,
    start1 = Offset(x = 0.5.dp.toPx(), y = 0.dp.toPx()),
    end1 = Offset(x = 14.5.dp.toPx(), y = 14.dp.toPx()),
    start2 = Offset(x = 14.5.dp.toPx(), y = 0.dp.toPx()),
    end2 = Offset(x = 0.5.dp.toPx(), y = 14.dp.toPx()),
    colorX = colorX,
)

// X Image adjusted for GetPlayersPrefsDialog
fun DrawScope.drawXinDialog(colorX: Color) = drawXPreview(
    strokeWidth = 5.dp,
    start1 = Offset(x = 3.dp.toPx(), y = 3.dp.toPx()),
    end1 = Offset(x = 32.dp.toPx(), y = 32.dp.toPx()),
    start2 = Offset(x = 32.dp.toPx(), y = 3.dp.toPx()),
    end2 = Offset(x = 3.dp.toPx(), y = 32.dp.toPx()),
    colorX = colorX,
)

// X Image adjusted for ColorSelectionDialog
fun DrawScope.drawXinColorDialog(colorX: Color) = drawXPreview(
    strokeWidth = 4.dp,
    start1 = Offset(x = 2.dp.toPx(), y = 2.dp.toPx()),
    end1 = Offset(x = 28.dp.toPx(), y = 28.dp.toPx()),
    start2 = Offset(x = 28.dp.toPx(), y = 2.dp.toPx()),
    end2 = Offset(x = 2.dp.toPx(), y = 28.dp.toPx()),
    colorX = colorX,
)
