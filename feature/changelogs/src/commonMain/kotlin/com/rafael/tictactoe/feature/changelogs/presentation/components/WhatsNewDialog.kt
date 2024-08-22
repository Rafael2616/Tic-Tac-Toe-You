package com.rafael.tictactoe.feature.changelogs.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NewReleases
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.toRoute
import com.rafael.tictactoe.core.navigation.Changelogs
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.close
import com.rafael.tictactoe.core.resources.see_history
import com.rafael.tictactoe.core.resources.whats_new
import com.rafael.tictactoe.core.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.changelogs.data.ChangelogGetter
import com.rafael.tictactoe.feature.changelogs.presentation.util.styledChangelog
import org.jetbrains.compose.resources.stringResource

@Composable
fun WhatsNewDialog(
    onEvent: (SettingsEvent) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    var versionNumber by remember { mutableStateOf("") }
    var changelog by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        ChangelogGetter.getCurrent().also {
            versionNumber = it.first
            changelog = it.second
        }
    }

    AlertDialog(
        onDismissRequest = {
            onEvent(SettingsEvent.HideWhatsNewDialog)
        },
        icon = {
            Icon(
                imageVector = Icons.Outlined.NewReleases,
                contentDescription = "New Releases",
                tint = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier
                    .size(35.dp),
            )
        },
        title = {
            Text(
                text = stringResource(Res.string.whats_new) + " v$versionNumber",
                style = MaterialTheme.typography.titleLarge,
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
            ) {
                Text(
                    text = styledChangelog(changelog),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth(),
                )
            }
        },
        confirmButton = {
            if (navController.currentBackStackEntry?.toRoute<Changelogs>()?.screenName != "changelogsScreen") {
                OutlinedButton(
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    onClick = {
                        onEvent(SettingsEvent.HideWhatsNewDialog)
                        navController.navigate(Changelogs("changelogsScreen"))
                    },
                ) {
                    Text(text = stringResource(Res.string.see_history))
                }
            }
        },
        dismissButton = {
            Button(
                contentPadding = PaddingValues(horizontal = 12.dp),
                onClick = { onEvent(SettingsEvent.HideWhatsNewDialog) },
            ) {
                Text(stringResource(Res.string.close))
            }
        },
        modifier = modifier
            .height(440.dp)
            .width(360.dp),
    )
}
