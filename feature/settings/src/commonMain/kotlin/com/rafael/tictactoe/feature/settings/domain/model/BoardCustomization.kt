package com.rafael.tictactoe.feature.settings.domain.model

import androidx.annotation.FloatRange

data class BoardCustomization(
    val name: String,
    @param:FloatRange val valueRange: ClosedFloatingPointRange<Float>,
    val value: Float,
    val onValueChange: (Float) -> Unit,
    val steps: Int,
)
