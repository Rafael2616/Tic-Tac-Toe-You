package com.rafael.tictactoe.game.presentation.utils.draws

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.Dp

fun DrawScope.drawOPreview(
    center: Offset,
    radius: Dp,
    stroke: Stroke,
    colorO: Color
) {
    drawCircle(
        center = center,
        radius = radius.toPx(),
        style = stroke,
        color = colorO
    )
}

//O Image adjusted for ToastImageHandler
fun DrawScope.drawOinToast(colorO: Color) = drawOPreview(
    center = Offset(8.dp.toPx(), 8.dp.toPx()),
    radius = 8.dp,
    stroke = Stroke(width = 3.dp.toPx()),
    colorO = colorO
)

//O Image adjusted for VictoryCounter
fun DrawScope.drawOinCounter(colorO: Color) = drawOPreview(
    center = Offset(33.dp.toPx(), 10.dp.toPx()),
    radius = 7.dp,
    stroke = Stroke(width = 3.dp.toPx()),
    colorO = colorO
)