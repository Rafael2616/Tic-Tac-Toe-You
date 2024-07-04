package com.rafael.tictactoe.feature.settings.domain.profilepicture

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri

interface ProfilePictureUtils {

    fun savePictureFromUri(context: Context, imageUri: Uri)

    fun getPictureUriIfExists(): Uri

    fun getPictureBitmap(): Bitmap

    fun deletePicture(): Unit
}
