package dev.icerock.moko.permissions

class PermissionsControllerImpl : PermissionsController {
    override suspend fun providePermission(permission: Permission) {
        when (permission) {
            Permission.WRITE_STORAGE -> {
                // Not needed on Wasm
            }

            else -> {
                throw UnsupportedOperationException("Permission not supported on Wasm")
            }
        }
    }

    override suspend fun isPermissionGranted(permission: Permission): Boolean = when (permission) {
        Permission.WRITE_STORAGE -> true

        else -> true
    }

    override suspend fun getPermissionState(permission: Permission): PermissionState =
        when (permission) {
            Permission.WRITE_STORAGE -> PermissionState.Granted

            else -> PermissionState.Granted
        }

    override fun openAppSettings() {
        // TODO("Not yet implemented")
    }

    override fun bind() {
        // TODO("Not yet implemented")
    }
}
