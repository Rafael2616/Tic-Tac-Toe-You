package com.smarttoolfactory.extendedcolors.util

import androidx.compose.ui.graphics.Color
import com.smarttoolfactory.extendedcolors.util.ColorUtil.colorIntToARGBArray
import com.smarttoolfactory.extendedcolors.util.ColorUtil.colorIntToRGBArray

object HexUtil {
    /*
    HEX-ColorInt Conversion
     */
    fun hexToColorInt(colorString: String): Int {
        val hex = if (colorString.startsWith("#")) colorString else "#$colorString"
        return when (hex.length) {
            4 -> {
                val r = hex[1].digitToInt(16)
                val g = hex[2].digitToInt(16)
                val b = hex[3].digitToInt(16)
                (0xFF shl 24) or (r shl 20) or (r shl 16) or (g shl 12) or (g shl 8) or (b shl 4) or b
            }
            7 -> {
                val r = hex.substring(1, 3).toInt(16)
                val g = hex.substring(3, 5).toInt(16)
                val b = hex.substring(5, 7).toInt(16)
                (0xFF shl 24) or (r shl 16) or (g shl 8) or b
            }
            9 -> {
                hex.substring(1).toLong(16).toInt()
            }
            else -> throw IllegalArgumentException("Invalid hex color: $hex")
        }
    }

    /*
        HEX-RGB Conversion
     */
    fun hexToRGB(colorString: String): IntArray {
        val colorInt = hexToColorInt(colorString)
        return colorIntToRGBArray(colorInt)
    }

    fun hexToRGB(colorString: String, rgbIn: IntArray) {
        val colorInt = hexToColorInt(colorString)
        colorIntToRGBArray(colorInt, rgbIn)
    }

    fun hexToARGB(colorString: String): IntArray {
        val colorInt = hexToColorInt(colorString)
        return colorIntToARGBArray(colorInt)
    }

    fun hexToARGB(colorString: String, argbIn: IntArray) {
        val colorInt = hexToColorInt(colorString)
        colorIntToARGBArray(colorInt, argbIn)
    }

    fun hexToColor(colorString: String): Color = Color(hexToColorInt(colorString))
}
