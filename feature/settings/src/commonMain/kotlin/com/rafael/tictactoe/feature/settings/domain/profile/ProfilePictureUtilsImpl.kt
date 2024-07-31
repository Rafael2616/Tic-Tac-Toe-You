package com.rafael.tictactoe.feature.settings.domain.profile

import androidx.compose.ui.graphics.ImageBitmap
import coil3.Uri

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class ProfilePictureUtilsImpl() : ProfilePictureUtils {
    override fun savePictureFromUri(imageUri: Uri)
    override fun getPictureUriIfExists(): Uri?
    override fun getPictureBitmap(): ImageBitmap
    override fun deletePicture()
}
