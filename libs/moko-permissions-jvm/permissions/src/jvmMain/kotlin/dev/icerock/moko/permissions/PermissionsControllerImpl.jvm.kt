package dev.icerock.moko.permissions

import java.io.File

class PermissionsControllerImpl : PermissionsController {
    override suspend fun providePermission(permission: Permission) {
        when (permission) {
            Permission.WRITE_STORAGE -> {
                val storageDir = File(System.getProperty("user.home"))
                if (!storageDir.canWrite()) {
                    throw SecurityException("Storage not writable")
                }
            }

            else -> {
                throw UnsupportedOperationException("Permission not supported on JVM")
            }
        }
    }

    override suspend fun isPermissionGranted(permission: Permission): Boolean = when (permission) {
        Permission.WRITE_STORAGE -> {
            val storageDir = File(System.getProperty("user.home"))
            storageDir.canWrite()
        }

        else -> true
    }

    override suspend fun getPermissionState(permission: Permission): PermissionState =
        when (permission) {
            Permission.WRITE_STORAGE -> {
                val storageDir = File(System.getProperty("user.home"))
                if (storageDir.canWrite()) {
                    PermissionState.Granted
                } else {
                    PermissionState.Denied
                }
            }

            else -> PermissionState.Granted
        }

    override fun openAppSettings() {
        // TODO("Not yet implemented")
    }

    override fun bind() {
        // TODO("Not yet implemented")
    }
}
