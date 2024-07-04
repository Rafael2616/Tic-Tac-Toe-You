package com.rafael.tictactoe.feature.settings.domain.animations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import com.rafael.tictactoe.feature.settings.domain.enums.RGBColor
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RGBAnimation {
    private val rgbColors = mapOf(
        RGBColor.Red to Color.Red,
        RGBColor.BlazeOrange to Color(0xFFFF6A00),
        RGBColor.SuperNova to Color(0xFFFFCB00),
        RGBColor.Yellow to Color.Yellow,
        RGBColor.BrightGreen to Color(0xFF6EFF00),
        RGBColor.Green to Color.Green,
        RGBColor.SpringGreen to Color(0xFF00FF8B),
        RGBColor.CyanAqua to Color(0xFF00FFEF),
        RGBColor.AzureRadiance to Color(0xFF008CFF),
        RGBColor.Blue to Color.Blue,
        RGBColor.ElectricVolt to Color(0xFF9D0DFF),
        RGBColor.Magenta to Color(0xFFFF11F3),
        RGBColor.Rose to Color(0xFFFF0088),
    )

    fun animate() = flow {
        while (true) {
            rgbColors.values.forEach { color -> emit(color) }
        }
    }.flowOn(Dispatchers.IO)
}

@Composable
fun rememberRGBAnimation() = remember { RGBAnimation() }
