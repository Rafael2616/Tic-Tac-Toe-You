package com.rafael.tictactoe.core.settings.data.database


import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlin.reflect.KClass
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCObjectVar
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSError
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

actual inline fun <reified T : RoomDatabase> getRoomDatabase(
    klass: KClass<T>,
    databaseName: String,
): RoomDatabase.Builder<T> {
    val dbFilePath = documentDirectory() + "/" + "$databaseName.db"
    return Room.databaseBuilder<T>(name = dbFilePath)
        .setDriver(BundledSQLiteDriver())
}

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
fun documentDirectory(): String {
    memScoped {
        val errorPtr = alloc<ObjCObjectVar<NSError?>>()
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = errorPtr.ptr,
        )
        if (documentDirectory != null) {
            return requireNotNull(documentDirectory.path) {
                """Couldn't determine the document directory.
                  URL $documentDirectory does not conform to RFC 1808.
                """.trimIndent()
            }
        } else {
            val error = errorPtr.value
            val localizedDescription = error?.localizedDescription ?: "Unknown error occurred"
            error("Couldn't determine document directory. Error: $localizedDescription")
        }
    }
}

