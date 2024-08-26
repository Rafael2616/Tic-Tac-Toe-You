package com.rafael.tictactoe.feature.settings.data.utils

import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asSkiaBitmap
import java.io.ByteArrayOutputStream
import javax.imageio.ImageIO
import org.jetbrains.skiko.toBufferedImage

actual fun ImageBitmap.toByteArray(): ByteArray {
    val bufferedImage = asSkiaBitmap().toBufferedImage()
    val byteArrayOutputStream = ByteArrayOutputStream()
    ImageIO.write(bufferedImage, "png", byteArrayOutputStream)
    return byteArrayOutputStream.toByteArray()
}
