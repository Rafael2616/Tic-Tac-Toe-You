package com.rafael.tictactoe.feature.settings.data.profile

import android.app.Application
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.webkit.MimeTypeMap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import coil3.Uri
import coil3.toAndroidUri
import com.rafael.tictactoe.feature.settings.domain.profile.ProfilePictureUtils
import org.koin.java.KoinJavaComponent.inject

internal class AndroidProfilePictureUtils : ProfilePictureUtils {
    override fun getPictureBitmap(imageUri: Uri): ImageBitmap {
        val context: Application by inject(Application::class.java)
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(imageUri.toAndroidUri())!! // return
        val imageMimeType = contentResolver.getType(imageUri.toAndroidUri())
        val imageExtension = MimeTypeMap.getSingleton().getExtensionFromMimeType(imageMimeType)
        val outputStream = context.openFileOutput("profilePicture.$imageExtension", 0)
        val options = BitmapFactory.Options()
        options.inSampleSize = 3

        val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
        bitmap?.compress(compressionFormat(imageExtension), 80, outputStream)

        return bitmap!!.asImageBitmap()
    }

    private fun compressionFormat(extension: String?): CompressFormat = when (extension) {
        "jpg", "jpeg" -> CompressFormat.JPEG
        "png" -> CompressFormat.PNG
        else -> CompressFormat.JPEG
    }
}
