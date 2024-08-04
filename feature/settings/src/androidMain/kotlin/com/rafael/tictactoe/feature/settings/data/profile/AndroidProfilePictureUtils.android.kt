package com.rafael.tictactoe.feature.settings.data.profile

import android.app.Application
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.webkit.MimeTypeMap
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import coil3.Uri
import coil3.toAndroidUri
import coil3.toCoilUri
import com.rafael.tictactoe.feature.settings.BuildConfig
import com.rafael.tictactoe.feature.settings.domain.profile.ProfilePictureUtils
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import org.koin.java.KoinJavaComponent.inject

internal class AndroidProfilePictureUtils : ProfilePictureUtils {
    override fun savePictureFromUri(imageUri: Uri) {
        val context: Application by inject(Application::class.java)
        val contentResolver = context.contentResolver
        val inputStream = contentResolver.openInputStream(imageUri.toAndroidUri()) ?: return
        val directory = File(PICTURE_PATH)
        if (!directory.exists()) {
            directory.mkdirs()
        }

        val filesInPictureDir = directory.listFiles()
        filesInPictureDir?.forEach { file ->
            if (file.isFile && file.nameWithoutExtension == "profilePicture") {
                file.delete()
            }
        }

        val imageMimeType = contentResolver.getType(imageUri.toAndroidUri())
        val imageExtension = MimeTypeMap.getSingleton().getExtensionFromMimeType(imageMimeType)
        val targetFile = File(PICTURE_PATH + "profilePicture.$imageExtension")
        val outputStream = FileOutputStream(targetFile)
        val options = BitmapFactory.Options()
        options.inSampleSize = 3

        val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
        bitmap?.compress(compressionFormat(imageExtension), 80, outputStream)

        outputStream.flush()
        outputStream.close()
        inputStream.close()
    }

    override fun getPictureUriIfExists(): Uri? {
        var uriDirWithFileNameExt: Uri? = null
        val directory = File(PICTURE_PATH)

        if (directory.exists() && directory.isDirectory) {
            val files = directory.listFiles()
            if (files != null && files.isNotEmpty()) {
                files.forEach { file ->
                    if (file.isFile && file.nameWithoutExtension == "profilePicture") {
                        uriDirWithFileNameExt = android.net.Uri.parse(PICTURE_PATH + file.name).toCoilUri()
                    }
                }
            }
        }
        return uriDirWithFileNameExt
    }

    override fun getPictureBitmap(): ImageBitmap {
        return BitmapFactory.decodeStream(
            FileInputStream(getPictureUriIfExists()?.path!!),
        ).asImageBitmap()
    }

    override fun deletePicture() {
        val directory = File(PICTURE_PATH)
        val filesInPictureDir = directory.listFiles()
        filesInPictureDir?.forEach { file ->
            if (file.isFile && file.nameWithoutExtension == "profilePicture") {
                file.delete()
            }
        }
    }

    private fun compressionFormat(extension: String?): CompressFormat {
        return when (extension) {
            "jpg", "jpeg" -> CompressFormat.JPEG
            "png" -> CompressFormat.PNG
            else -> CompressFormat.JPEG
        }
    }

    companion object {
        private val buildType = if (BuildConfig.BUILD_TYPE == "release") "" else "." + BuildConfig.BUILD_TYPE
        val PICTURE_PATH = "/storage/emulated/0/Android/data/com.rafael.tictactoe$buildType/files/userProfile/"
    }
}
