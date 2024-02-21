package com.rafael.tictactoe.common.domain.util

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import java.io.File
import java.io.FileOutputStream

class ProfilePictureUtils() {

    fun savePictureFromUri(context: Context, imageUri: Uri): Uri {
        val contentResolver = context.contentResolver

        val inputStream = contentResolver.openInputStream(imageUri) ?: return Uri.EMPTY
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

        val buffer = ByteArray(1024)
        var bytesRead: Int
        while (inputStream.read(buffer).also { bytesRead = it } != -1) {
            outputStream.write(buffer, 0, bytesRead)
        }

        outputStream.flush()
        outputStream.close()
        inputStream.close()

        return Uri.parse(targetFile.absolutePath)
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

    fun deletePicture() {
        val directory = File(PICTURE_PATH)
        val filesInPictureDir = directory.listFiles()
        filesInPictureDir?.forEach { file ->
            if (file.isFile && file.nameWithoutExtension == "profilePicture") {
                file.delete()
            }
        }
    }

    companion object {
        const val PICTURE_PATH = "/storage/emulated/0/Android/data/com.rafael.tictactoe/files/userProfile/"
    }
}
