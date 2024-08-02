package com.rafael.tictactoe.core.platform.permissions

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.icerock.moko.permissions.DeniedAlwaysException
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionState
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PermissionsViewModel(
    private val permissionsController: PermissionsController,
) : ViewModel() {
    var permissionsState = MutableStateFlow(PermissionsState())
        private set

    init {
        viewModelScope.launch {
            permissionsState.update {
                it.copy(
                    storagePermissionState = permissionsController.getPermissionState(Permission.STORAGE),
                    writeStoragePermissionState = permissionsController.getPermissionState(
                        Permission.WRITE_STORAGE,
                    ),
                    galleryPermissionState = permissionsController.getPermissionState(
                        Permission.GALLERY,
                    ),
                )
            }
        }
    }

    fun provideOrRequestStoragePermission() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.STORAGE)
                permissionsState.update { it.copy(storagePermissionState = PermissionState.Granted) }
            } catch (e: DeniedAlwaysException) {
                permissionsState.update { it.copy(storagePermissionState = PermissionState.DeniedAlways) }
            } catch (e: DeniedException) {
                permissionsState.update { it.copy(storagePermissionState = PermissionState.Denied) }
            } catch (e: RequestCanceledException) {
                e.printStackTrace()
            }
        }
    }

    fun provideOrRequestWriteStoragePermission() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.WRITE_STORAGE)
                permissionsState.update { it.copy(writeStoragePermissionState = PermissionState.Granted) }
            } catch (e: DeniedAlwaysException) {
                permissionsState.update { it.copy(writeStoragePermissionState = PermissionState.DeniedAlways) }
            } catch (e: DeniedException) {
                permissionsState.update { it.copy(writeStoragePermissionState = PermissionState.Denied) }
            } catch (e: RequestCanceledException) {
                e.printStackTrace()
            }
        }
    }

    fun provideOrRequestGalleryPermission() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.GALLERY)
                permissionsState.update { it.copy(galleryPermissionState = PermissionState.Granted) }
            } catch (e: DeniedAlwaysException) {
                permissionsState.update { it.copy(galleryPermissionState = PermissionState.DeniedAlways) }
            } catch (e: DeniedException) {
                permissionsState.update { it.copy(galleryPermissionState = PermissionState.Denied) }
            } catch (e: RequestCanceledException) {
                e.printStackTrace()
            }
        }
    }
}
