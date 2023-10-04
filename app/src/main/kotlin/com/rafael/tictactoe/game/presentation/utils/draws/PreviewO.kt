package com.rafael.tictactoe.game.presentation.utils.draws

import androidx.compose.foundation.Canvas
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