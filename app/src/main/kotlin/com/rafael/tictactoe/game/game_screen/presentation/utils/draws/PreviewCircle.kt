package com.rafael.tictactoe.game.presentation.utils.draws

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun DrawScope.drawCircle(
    center: Offset,
    radius: Dp,
    style: Stroke,
    color: Color,
) {
    drawCircle(
        center = center,
        radius = radius.toPx(),
        style = style,
        color = color,
    )
}

// Circle Image adjusted for ToastImageHandler
fun DrawScope.drawCircleinToast(color: Color) = drawCircle(
    center = Offset(8.dp.toPx(), 8.dp.toPx()),
    radius = 8.dp,
    style = Stroke(width = 3.dp.toPx()),
    color = color,
)

// Circle Image adjusted for VictoryCounter
fun DrawScope.drawCircleinCounter(color: Color) = drawCircle(
    center = Offset(7.5.dp.toPx(), 7.dp.toPx()),
    radius = 7.dp,
    style = Stroke(width = 3.dp.toPx()),
    color = color,
)

// Circle Image adjusted for GetPlayersPrefsDialog
fun DrawScope.drawCircleinDialog(color: Color) = drawCircle(
    center = Offset(18.dp.toPx(), 18.dp.toPx()),
    radius = 15.dp,
    style = Stroke(width = 5.dp.toPx()),
    color = color,
)

// Circle Image adjusted for ColorSelectionDialog
fun DrawScope.drawCircleinColorDialog(color: Color) = drawCircle(
    center = Offset(10.dp.toPx(), 10.dp.toPx()),
    radius = 10.dp,
    style = Stroke(width = 4.dp.toPx()),
    color = color,
)
