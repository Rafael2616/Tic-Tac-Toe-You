package com.rafael.tictactoe.feature.settings.domain.profile

import androidx.compose.ui.graphics.ImageBitmap
import coil3.Uri

actual class ProfilePictureUtilsImpl : ProfilePictureUtils {
    actual override fun savePictureFromUri(imageUri: Uri) {
        TODO("savePictureFromUri(imageUri: Uri) Not yet implemented on IOS")
    }
    actual override fun getPictureUriIfExists(): Uri? {
        TODO("getPictureUriIfExists(): Uri? Not yet implemented on IOS")
    }
    actual override fun getPictureBitmap(): ImageBitmap {
        TODO("getPictureBitmap(): ImageBitmap Not yet implemented on IOS")
    }

    actual override fun deletePicture() {
        TODO("deletePicture() Not yet implemented on IOS")
    }
}
