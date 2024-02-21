package com.rafael.tictactoe.settings.settingsScreen.presentation.components

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AddPhotoAlternate
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState

@Composable
fun ProfilePictureMenu(
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
    onDeleteImage: () -> Unit,
    onSaveImage: (uri: Uri) -> Unit,
    modifier: Modifier = Modifier,
) {
    val profilePicture = state.profilePictureBitmap
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        uri?.let { onSaveImage(uri) }
    }
    val activity = LocalContext.current as ComponentActivity

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 80.dp, end = 8.dp)
            .wrapContentSize(Alignment.TopEnd),
    ) {
        DropdownMenu(
            expanded = state.showProfilePictureMenu,
            onDismissRequest = { onEvent(SettingsEvent.HideProfilePictureMenu) },
        ) {
            DropdownMenuItem(
                text = { Text(text = stringResource(if (profilePicture == null) R.string.add_photo else R.string.change_photo)) },
                onClick = {
                    if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                        launcher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                    } else {
                        ActivityCompat.requestPermissions(
                            activity,
                            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                            0,
                        )
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
                    text = { Text(stringResource(R.string.remove_photo)) },
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
