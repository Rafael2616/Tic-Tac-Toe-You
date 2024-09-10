package com.rafael.tictactoe.feature.settings.domain.profile

import androidx.compose.ui.graphics.ImageBitmap
import coil3.Uri

actual class ProfilePictureUtilsImpl actual constructor() : ProfilePictureUtils {
    actual override fun getPictureBitmap(imageUri: Uri): ImageBitmap = ImageBitmap(1, 1)
}
