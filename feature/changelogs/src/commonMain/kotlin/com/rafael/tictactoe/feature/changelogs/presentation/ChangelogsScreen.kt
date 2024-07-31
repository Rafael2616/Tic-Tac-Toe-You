package com.rafael.tictactoe.feature.changelogs.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.NewReleases
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rafael.tictactoe.core.domain.util.getVersionName
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.current
import com.rafael.tictactoe.core.resources.update_history
import com.rafael.tictactoe.core.ui.theme.AppTheme
import com.rafael.tictactoe.feature.changelogs.data.getChangelogFiles
import com.rafael.tictactoe.feature.changelogs.presentation.components.WhatsNewDialog
import com.rafael.tictactoe.feature.changelogs.presentation.util.styledChangelog
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@ExperimentalMaterial3Api
@ExperimentalTextApi
fun ChangelogsScreen(
    navController: NavController,
    settingsState: SettingsState,
    modifier: Modifier = Modifier,
    changelogs: List<Pair<String, String>> = getChangelogFiles(),
    onEvent: (SettingsEvent) -> Unit,
) {
    var isGoBackClicked by rememberSaveable { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val version = getVersionName()
    val currentVersion = if (version.contains("-")) version.split("-")[0] else version
    val uriHandler = LocalUriHandler.current
    val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(stringResource(Res.string.update_history))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (!isGoBackClicked) {
                                isGoBackClicked = true
                                navController.popBackStack()
                            }
                        },
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Go Back",
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = { onEvent(SettingsEvent.ShowWhatsNewDialog) },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.NewReleases,
                            contentDescription = "Current rel notes",
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
        ) {
            changelogs.forEach { (version, changelog) ->
                val styledChangelog = styledChangelog(changelog)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp),
                    ),
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterVertically),
                    ) {
                        Text(
                            text = "v" + if (currentVersion == version) version + " - " + stringResource(Res.string.current) else version,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                            ),
                        )
                        Text(
                            text = styledChangelog,
                            modifier = Modifier
                                .pointerInput(Unit) {
                                    detectTapGestures { pos ->
                                        layoutResult.value?.let { layoutResult ->
                                            val position = layoutResult.getOffsetForPosition(pos)
                                            val link = styledChangelog.getStringAnnotations(position, position).firstOrNull()
                                            if (link?.tag == "URL") {
                                                uriHandler.openUri(link.item)
                                            }
                                        }
                                    }
                                },
                            onTextLayout = {
                                layoutResult.value = it
                            },
                        )
                    }
                }
            }
        }
    }

    AnimatedVisibility(settingsState.showWhatsNewDialog) {
        WhatsNewDialog(
            onEvent,
            navController,
        )
    }
}

@Preview
// @PreviewLightDark
// @PreviewScreenSizes
@Composable
@ExperimentalMaterial3Api
@ExperimentalTextApi
private fun ChangelogsScreenPreview() {
    AppTheme(
        state = SettingsState(),
        onEvent = {},
    ) {
        ChangelogsScreen(
            navController = rememberNavController(),
            changelogs = listOf(
                Pair("1.0.0", "Initial release"),
                Pair("1.0.1", "Fixes: fix one issue"),
                Pair(
                    "1.0.2",
                    "" +
                        "Added: add new feature" +
                        "\nhttps://test.com.br/",
                ),
            ),
            settingsState = SettingsState(),
            onEvent = {},
        )
    }
}
