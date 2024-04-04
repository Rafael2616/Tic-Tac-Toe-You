package com.rafael.tictactoe.settings.changelogsScreen.presentation

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.getChangelogFiles
import com.rafael.tictactoe.common.domain.util.getVersionName
import com.rafael.tictactoe.settings.changelogsScreen.presentation.components.WhatsNewDialog
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodel.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun ChangelogsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    settingsVM: SettingsViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val changelogs = context.getChangelogFiles()
    var isGoBackClicked by rememberSaveable { mutableStateOf(false) }
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val version = getVersionName()
    val currentVersion = if (version.contains("-")) version.split("-")[0] else version
    val state by settingsVM.state.collectAsState()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(stringResource(R.string.update_history))
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
                        onClick = { settingsVM.onEvent(SettingsEvent.ShowWhatsNewDialog) },
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
                            text = "v" + if (currentVersion == version) version + " - " + stringResource(R.string.current) else version,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                            ),
                        )
                        Text(
                            text = changelog,
                        )
                    }
                }
            }
        }
    }

    AnimatedVisibility(state.showWhatsNewDialog) {
        WhatsNewDialog(settingsVM::onEvent)
    }
}
