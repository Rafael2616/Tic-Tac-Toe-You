package com.rafael.tictactoe.feature.settings.domain.profile

import androidx.compose.ui.graphics.ImageBitmap
import coil3.Uri

interface ProfilePictureUtils {

    fun savePictureFromUri(imageUri: Uri)

    fun getPictureUriIfExists(): Uri?

    fun getPictureBitmap(): ImageBitmap

    fun deletePicture()
}
