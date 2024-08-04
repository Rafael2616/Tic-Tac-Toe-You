package com.rafael.tictactoe.feature.game.presentation.components.dialogs

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.leave
import com.rafael.tictactoe.core.resources.leave_game_description
import com.rafael.tictactoe.core.resources.leave_game_msg
import com.rafael.tictactoe.core.resources.stay
import com.rafael.tictactoe.feature.game.domain.model.GameEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ExitGameDialog(
    navController: NavController,
    onEvent: (GameEvent) -> Unit,
    onSettingsEvent: (SettingsEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isExitClicked by rememberSaveable { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = {
            onEvent(GameEvent.DismissExitDialog)
        },
        icon = { Icon(Icons.AutoMirrored.Outlined.Logout, contentDescription = null) },
        title = {
            Text(stringResource(Res.string.leave_game_msg))
        },
        text = {
            Text(
                text = stringResource(Res.string.leave_game_description),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
            )
        },
        confirmButton = {
            OutlinedButton(
                onClick = {
                    onEvent(GameEvent.DismissExitDialog)
                },
            ) {
                Text(stringResource(Res.string.stay))
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    if (!isExitClicked) {
                        isExitClicked = true
                        onEvent(GameEvent.ExitGame(onSettingsEvent))
                        navController.popBackStack()
                    }
                },
            ) {
                Text(stringResource(Res.string.leave))
            }
        },
        modifier = modifier
            .width(350.dp)
            .wrapContentHeight(),

    )
}
