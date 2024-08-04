package com.smarttoolfactory.extendedcolors.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import com.smarttoolfactory.extendedcolors.util.RGBUtil.alpha
import com.smarttoolfactory.extendedcolors.util.RGBUtil.argbToHex
import com.smarttoolfactory.extendedcolors.util.RGBUtil.rgbToHSL
import com.smarttoolfactory.extendedcolors.util.RGBUtil.rgbToHSV
import com.smarttoolfactory.extendedcolors.util.RGBUtil.rgbToHex

object ColorUtil {

    /**
     * Convert Jetpack Compose [Color] to HSV (hue-saturation-value) components.
     * ```
     * Hue is [0 .. 360)
     * Saturation is [0...1]
     * Value is [0...1]
     * ```
     * @param hslIn 3 element array which holds the input HSL components.
     */
    fun colorToHSV(color: Color, hslIn: FloatArray) {
        val rgbArray: IntArray = colorIntToRGBArray(color.toArgb())
        val red = rgbArray[0]
        val green = rgbArray[1]
        val blue = rgbArray[2]
        rgbToHSV(red, green, blue, hslIn)
    }

    /**
     * Convert Jetpack Compose [Color] to HSV (hue-saturation-value) components.
     * ```
     * Hue is [0 .. 360)
     * Saturation is [0...1]
     * Value is [0...1]
     * ```
     */
    fun colorToHSV(color: Color): FloatArray {
        val rgbArray: IntArray = colorIntToRGBArray(color.toArgb())
        val red = rgbArray[0]
        val green = rgbArray[1]
        val blue = rgbArray[2]
        return rgbToHSV(red, green, blue)
    }

    /**
     * Convert Jetpack Compose [Color] to HSV (hue-saturation-value) components.
     * ```
     * hsl[0] is Hue [0 .. 360)
     * hsl[1] is Saturation [0...1]
     * hsl[2] is Lightness [0...1]
     * ```
     * @param hslIn 3 element array which holds the input HSL components.
     */
    fun colorToHSL(color: Color, hslIn: FloatArray) {
        val rgbArray: IntArray = colorIntToRGBArray(color.toArgb())
        val red = rgbArray[0]
        val green = rgbArray[1]
        val blue = rgbArray[2]
        rgbToHSL(red, green, blue, hslIn)
    }

    /**
     * Convert Jetpack Compose [Color] to HSV (hue-saturation-value) components.
     * ```
     * hsl[0] is Hue [0 .. 360)
     * hsl[1] is Saturation [0...1]
     * hsl[2] is Lightness [0...1]
     * ```
     */
    fun colorToHSL(color: Color): FloatArray {
        val rgbArray: IntArray = colorIntToRGBArray(color.toArgb())
        val red = rgbArray[0]
        val green = rgbArray[1]
        val blue = rgbArray[2]
        return rgbToHSL(red, green, blue)
    }

/*
    COLOR-RGB Conversions
 */

    /**
     * Convert Jetpack [Color] into 3 element array of red, green, and blue
     *```
     * rgb[0] is Red [0 .. 255]
     * rgb[1] is Green [0...255]
     * rgb[2] is Blue [0...255]
     * ```
     * @param color Jetpack Compose [Color]
     * @return 3 element array which holds the input RGB components.
     */
    fun colorToARGBArray(color: Color): IntArray {
        return colorIntToRGBArray(color.toArgb())
    }

    /**
     * Convert Jetpack [Color] into 3 element array of red, green, and blue
     *```
     * rgb[0] is Red [0 .. 255]
     * rgb[1] is Green [0...255]
     * rgb[2] is Blue [0...255]
     * ```
     * @param color Jetpack Compose [Color]
     * @return 3 element array which holds the input RGB components.
     */
    fun colorToRGBArray(color: Color): IntArray {
        return colorIntToRGBArray(color.toArgb())
    }

    /**
     * Convert Jetpack [Color] into 3 element array of red, green, and blue
     *```
     * rgb[0] is Red [0 .. 255]
     * rgb[1] is Green [0...255]
     * rgb[2] is Blue [0...255]
     * ```
     * @param color Jetpack Compose [Color]
     * @param rgbIn 3 element array which holds the input RGB components.
     */
    fun colorToRGBArray(color: Color, rgbIn: IntArray) {
        val argbArray = colorIntToRGBArray(color.toArgb())
        rgbIn[0] = argbArray[0]
        rgbIn[1] = argbArray[1]
        rgbIn[2] = argbArray[2]
    }

    fun colorToHex(color: Color): String {
        return rgbToHex(color.red, color.green, color.blue)
    }

    fun colorToHexAlpha(color: Color): String {
        return argbToHex(color.alpha, color.red, color.green, color.blue)
    }

    /**
     * Convert a RGB color in [Integer] form to HSV (hue-saturation-value) components.
     *  * For instance, red =255, green =0, blue=0 is -65536
     * ```
     * hsv[0] is Hue [0 .. 360)
     * hsv[1] is Saturation [0...1]
     * hsv[2] is Value [0...1]
     * ```
     */
    fun colorIntToHSV(intColor: Int): FloatArray {
        val color = Color(intColor)
        val hsv = FloatArray(3)
        hsv[0] = color.red
        hsv[1] = color.green
        hsv[2] = color.blue
        return hsv
    }

    /**
     * Convert a RGB color in [Integer] form to HSV (hue-saturation-value) components.
     *  * For instance, red =255, green =0, blue=0 is -65536
     *
     * ```
     * hsv[0] is Hue [0 .. 360)
     * hsv[1] is Saturation [0...1]
     * hsv[2] is Value [0...1]
     * ```
     * @param hsvIn 3 element array which holds the input HSV components.
     */
    fun colorIntToHSV(color: Int, hsvIn: FloatArray) {
        Color(hsvIn[0].fractionToRGBRange(), hsvIn[1].fractionToRGBRange(), hsvIn[2].fractionToRGBRange(), color.alpha)
    }

    /**
     * Convert RGB color [Integer] to HSL (hue-saturation-lightness) components.
     * ```
     * hsl[0] is Hue [0 .. 360)
     * hsl[1] is Saturation [0...1]
     * hsl[2] is Lightness [0...1]
     * ```
     */
    fun colorIntToHSL(color: Int): FloatArray {
        val c = Color(color)
        val hsl = floatArrayOf(0f, 0f, 0f)

        hsl[0] = rgbToHSL(c.red, c.green, c.blue, hsl)[0]
        hsl[1] = rgbToHSL(c.red, c.green, c.blue, hsl)[1]
        hsl[2] = rgbToHSL(c.red, c.green, c.blue, hsl)[2]

        return hsl
    }

    fun rgbToHSL(r: Float, g: Float, b: Float, hsl: FloatArray): FloatArray {
        val max = maxOf(r, g, b)
        val min = minOf(r, g, b)
        var h = 0f
        var s = 0f
        val l = (max + min) / 2

        if (max == min) {
            h = 0f
            s = 0f
        } else {
            val d = max - min
            s = if (l > 0.5f) d / (2 - max - min) else d / (max + min)
            when (max) {
                r -> h = (g - b) / d + (if (g < b) 6 else 0)
                g -> h = (b - r) / d + 2
                b -> h = (r - g) / d + 4
            }
            h /= 6
        }

        hsl[0] = h
        hsl[1] = s
        hsl[2] = l
        return hsl
    }

    /**
     * Convert RGB color [Integer] to HSL (hue-saturation-lightness) components.
     * ```
     * hsl[0] is Hue [0 .. 360)
     * hsl[1] is Saturation [0...1]
     * hsl[2] is Lightness [0...1]
     * ```
     */

    fun colorIntToHSL(color: Int, hslIn: FloatArray) {
        val c = Color(color)
        hslIn[0] = rgbToHSL(c.red, c.green, c.blue, hslIn)[0]
        hslIn[1] = rgbToHSL(c.red, c.green, c.blue, hslIn)[1]
        hslIn[2] = rgbToHSL(c.red, c.green, c.blue, hslIn)[2]
    }

/*
    ColorInt-RGB Conversions
 */
    /**
     * Convert Color [Integer] into 3 element array of red, green, and blue
     *```
     * rgb[0] is Red [0 .. 255]
     * rgb[1] is Green [0...255]
     * rgb[2] is Blue [0...255]
     * ```
     * @return 3 element array which holds the input RGB components.
     */
    fun colorIntToRGBArray(intColor: Int): IntArray {
        val color = Color(intColor)
        val red = color.red.fractionToRGBRange()
        val green = color.green.fractionToRGBRange()
        val blue = color.blue.fractionToRGBRange()
        return intArrayOf(red, green, blue)
    }

    /**
     * Convert Color [Integer] into 3 element array of red, green, and blue
     *```
     * rgb[0] is Red [0 .. 255]
     * rgb[1] is Green [0...255]
     * rgb[2] is Blue [0...255]
     * ```
     * @param rgbIn 3 element array which holds the input RGB components.
     */
    fun colorIntToRGBArray(intColor: Int, rgbIn: IntArray) {
        val color = Color(intColor)
        val red = color.red.fractionToRGBRange()
        val green = color.green.fractionToRGBRange()
        val blue = color.blue.fractionToRGBRange()

        rgbIn[0] = red
        rgbIn[1] = green
        rgbIn[2] = blue
    }

    /**
     * Convert Color [Integer] into 4 element array of alpha red, green, and blue
     *```
     * rgb[0] is Alpha [0 .. 255]
     * rgb[1] is Red [0 .. 255]
     * rgb[2] is Green [0...255]
     * rgb[3] is Blue [0...255]
     * ```
     * @return 4 element array which holds the input ARGB components.
     */
    fun colorIntToARGBArray(intColor: Int): IntArray {
        val color = Color(intColor)
        val red = color.red.fractionToRGBRange()
        val green = color.green.fractionToRGBRange()
        val blue = color.blue.fractionToRGBRange()
        val alpha = color.alpha.fractionToRGBRange()
        return intArrayOf(alpha, red, green, blue)
    }

    /**
     * Convert Color [Integer] into 4 element array of alpha red, green, and blue
     *```
     * rgb[0] is Alpha [0 .. 255]
     * rgb[1] is Red [0 .. 255]
     * rgb[2] is Green [0...255]
     * rgb[3] is Blue [0...255]
     * ```
     * @param argbIn 4 element array which holds the input ARGB components.
     */
    fun colorIntToARGBArray(intColor: Int, argbIn: IntArray) {
        val color = Color(intColor)
        val red = color.red.fractionToRGBRange()
        val green = color.green.fractionToRGBRange()
        val blue = color.blue.fractionToRGBRange()
        val alpha = color.alpha.fractionToRGBRange()

        argbIn[0] = alpha
        argbIn[1] = red
        argbIn[2] = green
        argbIn[3] = blue
    }
}
