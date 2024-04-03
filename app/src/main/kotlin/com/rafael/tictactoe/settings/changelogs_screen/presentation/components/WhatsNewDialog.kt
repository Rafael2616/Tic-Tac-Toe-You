package com.rafael.tictactoe.settings.changelogsScreen.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NewReleases
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.getChangelogFile
import com.rafael.tictactoe.common.domain.util.getVersionName
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent

@Suppress("ktlint:compose:modifier-not-used-at-root")
@Composable
fun WhatsNewDialog(
    onEvent: (SettingsEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val version = getVersionName()
    val versionNumber = if (version.contains("-")) version.split("-")[0] else version
    val context = LocalContext.current
    val changelog = getChangelogFile(context)

    Dialog(
        onDismissRequest = {
            onEvent(SettingsEvent.HideWhatsNewDialog)
        },
    ) {
        Card(
            shape = MaterialTheme.shapes.large,
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(15.dp),
            ),
            modifier = modifier
                .height(350.dp)
                .width(360.dp),
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .height(265.dp)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()),
            ) {
                Row(
                    modifier = Modifier
                        .height(40.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = stringResource(R.string.whats_new) + " v$versionNumber:",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontSize = 20.sp,
                            fontFamily = FontFamily.SansSerif,
                            fontWeight = FontWeight.Normal,
                        ),
                        color = MaterialTheme.colorScheme.inverseSurface,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .width(250.dp),
                    )
                    Icon(
                        imageVector = Icons.Outlined.NewReleases,
                        contentDescription = "New Releases",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(35.dp),
                    )
                }
                Spacer(Modifier.height(5.dp))
                Text(
                    text = "$changelog",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.inverseSurface,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                contentAlignment = Alignment.BottomEnd,
            ) {
                Button(
                    modifier = Modifier.padding(end = 16.dp, bottom = 10.dp),
                    contentPadding = PaddingValues(horizontal = 12.dp),
                    onClick = {
                        onEvent(SettingsEvent.HideWhatsNewDialog)
                        onEvent(SettingsEvent.ExecUIClickSound)
                    },
                ) {
                    Text(stringResource(R.string.close))
                }
            }
        }
    }
}
