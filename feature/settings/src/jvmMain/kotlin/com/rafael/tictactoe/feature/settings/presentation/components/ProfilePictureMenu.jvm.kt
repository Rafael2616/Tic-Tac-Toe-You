package com.rafael.tictactoe.feature.settings.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.unit.dp
import coil3.Uri
import com.rafael.tictactoe.core.platform.permissions.PermissionsState
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.add_photo
import com.rafael.tictactoe.core.resources.change_photo
import com.rafael.tictactoe.core.resources.remove_photo
import com.rafael.tictactoe.core.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.core.settings.domain.model.SettingsState
import dev.icerock.moko.permissions.PermissionState
import java.awt.FileDialog
import java.awt.Frame
import java.io.File
import javax.imageio.ImageIO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.invoke
import org.jetbrains.compose.resources.stringResource

@Composable
actual fun ProfilePictureMenu(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
    permissionsState: PermissionsState,
    requestGalleryPermission: () -> Unit,
    onDeleteImage: () -> Unit,
    onSaveImage: (uri: Uri) -> Unit,
    onSaveImageByBitmap: (imageBitmap: ImageBitmap?) -> Unit,
    modifier: Modifier,
) {
    var showFileDialog by remember { mutableStateOf(false) }
    val profilePicture = state.profilePictureBitmap

    OpenAndSaveImage(showFileDialog) {
        onSaveImageByBitmap(it)
        showFileDialog = false
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 80.dp, end = 8.dp)
            .wrapContentSize(Alignment.TopEnd),
    ) {
        DropdownMenu(
            expanded = state.showProfilePictureMenu,
            onDismissRequest = { onEvent(SettingsEvent.HideProfilePictureMenu) },
            shape = RoundedCornerShape(20.dp),
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(if (profilePicture == null) Res.string.add_photo else Res.string.change_photo)) },
                onClick = {
                    if (permissionsState.galleryPermissionState == PermissionState.Granted) {
                        showFileDialog = true
                    } else {
                        requestGalleryPermission()
                    }
                },
                leadingIcon = {
                    Icon(
                        imageVector = if (profilePicture == null) Icons.Outlined.AddPhotoAlternate else Icons.Outlined.Edit,
                        contentDescription = "Add or edit photo item",
                    )
                },
            )
            profilePicture?.let {
                DropdownMenuItem(
                    text = { Text(stringResource(Res.string.remove_photo)) },
                    onClick = { onDeleteImage() },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Delete,
                            contentDescription = "Delete item",
                        )
                    },
                )
            }
        }
    }
}

@Composable
fun OpenAndSaveImage(
    showFileDialog: Boolean,
    onSaveImageByBitmap: (imageBitmap: ImageBitmap?) -> Unit,
) {
    val latestOnSaveImageByBitmap by rememberUpdatedState(onSaveImageByBitmap)

    LaunchedEffect(showFileDialog) {
        if (showFileDialog) {
            val userHomeDir = System.getProperty("user.home")
            val imagesDir = File(userHomeDir, "Pictures")
            FileDialog(null as Frame?, "Select an image").apply {
                directory = imagesDir.absolutePath
                isVisible = true

                if (file != null) {
                    val imageFile = File(directory, file)
                    (Dispatchers.IO) {
                        val bufferedImage = ImageIO.read(imageFile)
                        latestOnSaveImageByBitmap(bufferedImage?.toComposeImageBitmap())
                    }
                }
            }
        }
    }
}
