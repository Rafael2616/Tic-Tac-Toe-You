package com.rafael.tictactoe.feature.settings.domain.profile

import androidx.compose.ui.graphics.ImageBitmap
import coil3.Uri

expect class ProfilePictureUtilsImpl() : ProfilePictureUtils {
    override fun getPictureBitmap(imageUri: Uri): ImageBitmap
}
