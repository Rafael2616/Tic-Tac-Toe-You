package com.rafael.tictactoe.settings.settingsScreen.domain.model

import androidx.annotation.FloatRange
import androidx.annotation.StringRes

data class BoardCustomization(
    @StringRes val name: Int,
    @FloatRange val valueRange: ClosedFloatingPointRange<Float>,
    val value: Float,
    val onValueChange: (Float) -> Unit,
    val steps: Int,
)
