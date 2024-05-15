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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rafael.tictactoe.core.navigation.destinations.Changelogs
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.feature.changelogs.data.getCurrentChangelogFile
import com.rafael.tictactoe.feature.changelogs.presentation.util.styledChangelog
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent

@Composable
@ExperimentalTextApi
fun WhatsNewDialog(
    onEvent: (SettingsEvent) -> Unit,
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val (versionNumber, changelog) = context.getCurrentChangelogFile()

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
                text = stringResource(R.string.whats_new) + " v$versionNumber",
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
            if (navController.currentBackStackEntry?.destination?.route != Changelogs) {
                OutlinedButton(
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    onClick = {
                        onEvent(SettingsEvent.HideWhatsNewDialog)
                        navController.navigate(Changelogs)
                    },
                ) {
                    Text(text = stringResource(R.string.see_history))
                }
            }
        },
        dismissButton = {
            Button(
                contentPadding = PaddingValues(horizontal = 12.dp),
                onClick = { onEvent(SettingsEvent.HideWhatsNewDialog) },
            ) {
                Text(stringResource(R.string.close))
            }
        },
        modifier = modifier
            .height(440.dp)
            .width(360.dp),
    )
}
