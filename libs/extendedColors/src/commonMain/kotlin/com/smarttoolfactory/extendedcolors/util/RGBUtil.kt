package com.smarttoolfactory.extendedcolors.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.smarttoolfactory.extendedcolors.util.ColorUtil.colorIntToHSL
import com.smarttoolfactory.extendedcolors.util.ColorUtil.colorIntToHSV

object RGBUtil {

    /**
     * Convert RGB red, green blue to HSV (hue-saturation-value) components.
     * ```
     * hsv[0] is Hue [0 .. 360)
     * hsv[1] is Saturation [0...1]
     * hsv[2] is Value [0...1]
     * ```
     * @param red  Red component [0..255] of the color
     * @param green  Green component [0..255] of the color
     * @param blue Blue component [0..255] of the color
     */
    fun rgbToHSV(
        red: Int,
        green: Int,
        blue: Int,
    ): FloatArray {
        val hsvOut = floatArrayOf(0f, 0f, 0f)
        Color(red, green, blue)
        return hsvOut
    }

    /**
     * Convert RGB red, green blue to HSV (hue-saturation-value) components.
     * ```
     * hsv[0] is Hue [0 .. 360)
     * hsv[1] is Saturation [0...1]
     * hsv[2] is Value [0...1]
     * ```
     * @param red  Red component [0..255] of the color
     * @param green  Green component [0..255] of the color
     * @param blue Blue component [0..255] of the color
     * @param hsvIn 3 element array which holds the output HSV components.
     */
    fun rgbToHSV(
        red: Int,
        green: Int,
        blue: Int,
        hsvIn: FloatArray,
    ) {
        val alpha = Color(hsvIn[0], hsvIn[1], hsvIn[2]).alpha
        Color(red, green, blue)
    }

    /**
     * Convert RGB red, green blue to HSL (hue-saturation-lightness) components.
     * ```
     * hsl[0] is Hue [0 .. 360)
     * hsl[1] is Saturation [0...1]
     * hsl[2] is Lightness [0...1]
     * ```
     * @param red  Red component [0..255] of the color
     * @param green  Green component [0..255] of the color
     * @param blue Blue component [0..255] of the color
     */
    fun rgbToHSL(
        red: Int,
        green: Int,
        blue: Int,
    ): FloatArray {
        val outHsl = floatArrayOf(0f, 0f, 0f)

        // ColorUtils.RGBToHSL(red, green, blue, outHsl)
        return outHsl
    }

    /**
     * Convert RGB red, green blue to HSL (hue-saturation-lightness) components.
     * ```
     * hsl[0] is Hue [0 .. 360)
     * hsl[1] is Saturation [0...1]
     * hsl[2] is Lightness [0...1]
     * ```
     * @param red  Red component [0..255] of the color
     * @param green  Green component [0..255] of the color
     * @param blue Blue component [0..255] of the color
     * @param hslIn 3 element array which holds the input HSL components.
     */
    fun rgbToHSL(
        red: Int,
        green: Int,
        blue: Int,
        hslIn: FloatArray,
    ) {
        // ColorUtils.RGBToHSL(red, green, blue, hslIn)
    }

    /**
     * Convert RGB red, green, blue components in [0f..1f] range to
     * HSV (hue-saturation-value) components.
     *
     * @param red  Red component [0..255] of the color
     * @param green  Green component [0..255] of the color
     * @param blue Blue component [0..255] of the color
     * @return 3 element array which holds the output RGB components.
     */
    fun rgbFloatToHSV(
        red: Float,
        green: Float,
        blue: Float,
    ): FloatArray {
        val colorInt = rgbToColorInt(red = red, green = green, blue = blue)
        val outHsl = floatArrayOf(0f, 0f, 0f)
        colorIntToHSV(colorInt, outHsl)
        return outHsl
    }

    /**
     * Convert RGB red, green, blue components in [0f..1f] range to
     * HSV (hue-saturation-value) components.
     *
     * @param red  Red component [0..255] of the color
     * @param green  Green component [0..255] of the color
     * @param blue Blue component [0..255] of the color
     * @param hsvIn 3 element array which holds the output HSV components.
     */
    fun rgbFloatToHSV(
        red: Float,
        green: Float,
        blue: Float,
        hsvIn: FloatArray,
    ) {
        val colorInt = rgbToColorInt(red = red, green = green, blue = blue)
        colorIntToHSV(colorInt, hsvIn)
    }

    /**
     * Convert RGB red, green blue in [0f..1f] range to HSL (hue-saturation-lightness) components.
     * ```
     * hsl[0] is Hue [0 .. 360)
     * hsl[1] is Saturation [0...1]
     * hsl[2] is Lightness [0...1]
     * ```
     * @param red  Red component [0f..1f] of the color
     * @param green  Green component [0f..1f] of the color
     * @param blue Blue component [0f..1f] of the color
     */
    fun rgbFloatToHSL(
        red: Float,
        green: Float,
        blue: Float,
    ): FloatArray {
        val colorInt = rgbToColorInt(red = red, green = green, blue = blue)
        val outHsl = floatArrayOf(0f, 0f, 0f)
        colorIntToHSL(colorInt, outHsl)
        return outHsl
    }

    /**
     * Convert RGB red, green blue in [0f..1f] range to HSL (hue-saturation-lightness) components.
     * ```
     * hsl[0] is Hue [0 .. 360)
     * hsl[1] is Saturation [0...1]
     * hsl[2] is Lightness [0...1]
     * ```
     * @param red  Red component [0f..1f] of the color
     * @param green  Green component [0f..1f] of the color
     * @param blue Blue component [0f..1f] of the color
     * @param hslIn 3 element array which holds the input HSL components.
     */
    fun rgbFloatToHSL(
        red: Float,
        green: Float,
        blue: Float,
        hslIn: FloatArray,
    ) {
        val colorInt = rgbToColorInt(red = red, green = green, blue = blue)
        colorIntToHSL(colorInt, hslIn)
    }

    /**
     * Return a color-int from alpha, red, green, blue components.
     * These component values should be [0..255], but there is no range check performed,
     * so if they are out of range, the returned color is undefined.
     *
     * @param red  Red component [0..255] of the color
     * @param green  Green component [0..255] of the color
     * @param blue Blue component [0..255] of the color
     */
    fun rgbToColorInt(
        red: Int,
        green: Int,
        blue: Int,
    ): Int {
        return Color(red, green, blue).toArgb()
    }

    /**
     * Return a color-int from alpha, red, green, blue components.
     * These component values should be [0f..1f], but there is no range check performed,
     * so if they are out of range, the returned color is undefined.
     *
     * @param red  Red component [0f..1f] of the color
     * @param green  Green component [0..1f] of the color
     * @param blue Blue component [0..1f] of the color
     *
     */
    fun rgbToColorInt(
        red: Float,
        green: Float,
        blue: Float,
    ): Int {
        return Color(red, green, blue).toArgb()
    }

    /**
     * Convert red, green, blue components [0..255] range in [Integer] to Hex format String
     */
    fun rgbToHex(red: Int, green: Int, blue: Int): String {
        return "#" +
            red.toString(16).padStart(2, '0') +
            green.toString(16).padStart(2, '0') +
            blue.toString(16).padStart(2, '0')
    }

    /**
     * Convert rgb array to Hex format String
     * ```
     * rgb[0] is Red [0 .. 255]
     * rgb[1] is Green [0...255]
     * rgb[2] is Blue [0...255]
     * ```
     */
    fun rgbToHex(rgb: IntArray): String {
        return "#" +
            rgb[0].toString(16).padStart(2, '0') +
            rgb[1].toString(16).padStart(2, '0') +
            rgb[2].toString(16).padStart(2, '0')
    }

    /**
     * Convert red, green, blue components [0f..1f] range in [Float] to Hex format String
     */
    fun rgbToHex(red: Float, green: Float, blue: Float): String {
        return "#" +
            red.fractionToRGBRange().toString(16).padStart(2, '0') +
            green.fractionToRGBRange().toString(16).padStart(2, '0') +
            blue.fractionToRGBRange().toString(16).padStart(2, '0')
    }

    /**
     * Return a color-int from alpha, red, green, blue components.
     * These component values should be [0..255], but there is no range check performed,
     * so if they are out of range, the returned color is undefined.
     *
     * @param alpha  Alpha component [0..255] of the color
     * @param red  Red component [0..255] of the color
     * @param green  Green component [0..255] of the color
     * @param blue Blue component [0..255] of the color
     */
    fun argbToColorInt(alpha: Int, red: Int, green: Int, blue: Int): Int {
        return (alpha shl 24) or (red shl 16) or (green shl 8) or blue
    }

    /**
     * Return a color-int from alpha, red, green, blue components.
     * These component values should be [0f..1f], but there is no range check performed,
     * so if they are out of range, the returned color is undefined.
     *
     * @param alpha  Alpha component [0f..1f] of the color
     * @param red  Red component [0f..1f] of the color
     * @param green  Green component [0..1f] of the color
     * @param blue Blue component [0..1f] of the color
     */
    fun argbToColorInt(alpha: Float, red: Float, green: Float, blue: Float): Int {
        val alphaInt = alpha.fractionToRGBRange()
        val redInt = red.fractionToRGBRange()
        val greenInt = green.fractionToRGBRange()
        val blueInt = blue.fractionToRGBRange()
        return (alphaInt shl 24) or (redInt shl 16) or (greenInt shl 8) or blueInt
    }

    /**
     * Convert alpha, red, green, blue components in [0..255] range argb to Hex format String
     *
     * ```
     * Alpha is [0 .. 255]
     * Red is [0 .. 255]
     * Green is [0...255]
     * Blue is [0...255]
     * ```
     */
    fun argbToHex(alpha: Int, red: Int, green: Int, blue: Int): String {
        return "#" + alpha.toString(16).padStart(2, '0') +
            red.toString(16).padStart(2, '0') +
            green.toString(16).padStart(2, '0') +
            blue.toString(16).padStart(2, '0')
    }

    /**
     * Convert alpha, red, green, blue components in [0f..1f] range in [Float] argb to Hex format String
     *
     * ```
     * Alpha is [0f .. 1f]
     * Red is [0f .. 1f]
     * Green is [0...1f]
     * Blue is [0...1f]
     * ```
     */
    fun argbToHex(alpha: Float, red: Float, green: Float, blue: Float): String {
        return "#" + alpha.fractionToRGBRange().toString(16).padStart(2, '0') +
            red.fractionToRGBRange().toString(16).padStart(2, '0') +
            green.fractionToRGBRange().toString(16).padStart(2, '0') +
            blue.fractionToRGBRange().toString(16).padStart(2, '0')
    }

/*
    RGB-HEX Conversions
 */
    /**
     * Get hex representation of a rgb Color in [Integer] format
     */

    fun Int.toRgbString(): String =
        "#${red.toString(16).padStart(2, '0')}" +
            green.toString(16).padStart(2, '0') +
            blue.toString(16).padStart(2, '0')
                .uppercase()

    /**
     * Get hex representation of a argb Color in [Integer] format
     */
    fun Int.toArgbString(): String =
        "#${alpha.toString(16).padStart(2, '0')}" +
            red.toString(16).padStart(2, '0') +
            green.toString(16).padStart(2, '0') +
            blue.toString(16).padStart(2, '0')
                .uppercase()

    private fun String.toStringComponent() =
        this.let { if (it.length == 1) "0$it" else it }

    private fun Int.toStringComponent(): String =
        this.toString(16).let { if (it.length == 1) "0$it" else it }

    inline val Int.alpha: Int
        get() = (this shr 24) and 0xFF

    inline val Int.red: Int
        get() = (this shr 16) and 0xFF

    inline val Int.green: Int
        get() = (this shr 8) and 0xFF

    inline val Int.blue: Int
        get() = this and 0xFF
}
