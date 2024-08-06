package com.rafael.tictactoe.feature.settings.domain.profile

import androidx.compose.ui.graphics.ImageBitmap
import coil3.Uri

interface ProfilePictureUtils {
    fun getPictureBitmap(imageUri: Uri): ImageBitmap
}
