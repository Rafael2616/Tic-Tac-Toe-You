package com.rafael.tictactoe.core.domain.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.CompressFormat
import android.graphics.BitmapFactory
import android.net.Uri
import android.webkit.MimeTypeMap
import com.rafael.tictactoe.BuildConfig
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class ProfilePictureUtils() {

    fun savePictureFromUri(context: Context, imageUri: Uri) {
        val contentResolver = context.contentResolver

        val inputStream = contentResolver.openInputStream(imageUri) ?: return
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

        val imageMimeType = contentResolver.getType(imageUri)
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

    fun getPictureUriIfExists(): Uri {
        var uriDirWithFileNameExt = Uri.EMPTY
        val directory = File(PICTURE_PATH)

        if (directory.exists() && directory.isDirectory) {
            val files = directory.listFiles()
            if (files != null && !files.isEmpty()) {
                files.forEach { file ->
                    if (file.isFile && file.nameWithoutExtension == "profilePicture") {
                        uriDirWithFileNameExt = Uri.parse(PICTURE_PATH + "${file.name}")
                    }
                }
            }
        }
        return uriDirWithFileNameExt
    }

    fun getPictureBitmap(): Bitmap {
        return BitmapFactory.decodeStream(
            FileInputStream(getPictureUriIfExists().path!!),
        )
    }

    fun deletePicture() {
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
            "jpg", "jpeg" -> Bitmap.CompressFormat.JPEG
            "png" -> Bitmap.CompressFormat.PNG
            else -> Bitmap.CompressFormat.JPEG
        }
    }

    companion object {
        // const val PICTURE_PATH = "/storage/emulated/0/Android/data/com.rafael.tictactoe.debug/files/userProfile/"
        const val PICTURE_PATH = "/storage/emulated/0/Android/data/${BuildConfig.APPLICATION_ID}/files/userProfile/"
    }
}
