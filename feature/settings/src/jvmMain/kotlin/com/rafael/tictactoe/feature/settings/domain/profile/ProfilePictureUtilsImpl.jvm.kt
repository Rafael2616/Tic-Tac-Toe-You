package com.rafael.tictactoe.feature.settings.domain.profile

import androidx.compose.ui.graphics.ImageBitmap
import coil3.Uri
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.decodeToImageBitmap

actual class ProfilePictureUtilsImpl actual constructor() : ProfilePictureUtils {
    @OptIn(ExperimentalResourceApi::class)
    actual override fun getPictureBitmap(imageUri: Uri): ImageBitmap =
        ByteArray(0).decodeToImageBitmap()
}
