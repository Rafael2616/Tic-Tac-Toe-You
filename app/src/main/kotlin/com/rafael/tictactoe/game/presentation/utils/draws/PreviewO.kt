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
    style: Stroke,
    colorO: Color
) {
    drawCircle(
        center = center,
        radius = radius.toPx(),
        style = style,
        color = colorO
    )
}

//O Image adjusted for ToastImageHandler
fun DrawScope.drawOinToast(colorO: Color) = drawOPreview(
    center = Offset(8.dp.toPx(), 8.dp.toPx()),
    radius = 8.dp,
    style = Stroke(width = 3.dp.toPx()),
    colorO = colorO
)

//O Image adjusted for VictoryCounter
fun DrawScope.drawOinCounter(colorO: Color) = drawOPreview(
    center = Offset(33.dp.toPx(), 10.dp.toPx()),
    radius = 7.dp,
    style = Stroke(width = 3.dp.toPx()),
    colorO = colorO
)

//O Image adjusted for GetPlayersPrefsDialog
fun DrawScope.drawOinDialog(colorO: Color) = drawOPreview(
    center = Offset(75.dp.toPx(), 30.dp.toPx()),
    radius = 15.dp,
    style = Stroke(width = 5.dp.toPx()),
    colorO = colorO
)