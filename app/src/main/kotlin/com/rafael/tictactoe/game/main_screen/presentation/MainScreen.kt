package com.rafael.tictactoe.game.mainScreen.presentation

import android.Manifest
import android.content.Intent
import android.content.res.Configuration
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.gameScreen.presentation.viewmodel.GameViewModel
import com.rafael.tictactoe.game.mainScreen.domain.model.MainEvent
import com.rafael.tictactoe.game.mainScreen.domain.model.ScreenItem
import com.rafael.tictactoe.game.mainScreen.presentation.components.WhatsNewDialog
import com.rafael.tictactoe.game.mainScreen.presentation.viewmodel.MainViewModel
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodel.SettingsViewModel

@Composable
fun MainScreen(
    navController: NavController,
    activity: ComponentActivity,
    modifier: Modifier = Modifier,
    viewModel: GameViewModel = hiltViewModel(),
    settingsVM: SettingsViewModel = hiltViewModel(),
    mainVM: MainViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val serviceIntent = Intent(context, MusicService::class.java)
    val state by settingsVM.state.collectAsState()
    var showStoragePermissinRequest = false
    val onEvent = mainVM::onEvent
    val onClick = viewModel::onEvent
    val orientation = LocalConfiguration.current.orientation

    val items = listOf(
        ScreenItem(
            name = "PvP",
            icon = Icons.Outlined.PlayArrow,
            tint = if (state.isDarkThemeOn) Color.Green else Color(0xFF299961),
            text = R.string.play,
        ),
        ScreenItem(
            name = "CPU",
            icon = Icons.Outlined.Memory,
            tint = Color.Red,
            text = R.string.vs_cpu,
        ),
        ScreenItem(
            name = "Online",
            icon = Icons.Outlined.Group,
            tint = MaterialTheme.colorScheme.onSurface,
            text = R.string.online_match,
        ),
        ScreenItem(
            name = "Settings",
            icon = Icons.Outlined.Settings,
            tint = MaterialTheme.colorScheme.onSurface,
            text = R.string.settings,
        ),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.inapp_title),
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 26.sp),
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(
            modifier = Modifier.height(3.dp),
        )
        Text(
            text = "Material You",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 35.dp else 0.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items.forEachIndexed { index, screen ->
                Card(
                    shape = MaterialTheme.shapes.large,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp),
                    ),
                    modifier = Modifier
                        .fillMaxWidth(if (orientation == Configuration.ORIENTATION_LANDSCAPE) 0.3f else 0.6f)
                        .height(58.dp)
                        .shadow(8.dp, RoundedCornerShape(36.dp))
                        .clip(RoundedCornerShape(36.dp))
                        .clickable {
                            onClick(GameEvent.ExecUIClickSound)
                            when (screen.name) {
                                "PvP" -> {
                                    navController.navigate(Destinations.Game.screen)
                                    onEvent(MainEvent.SetGameMode(GameMode.PvP))
                                }
                                "CPU" -> {
                                    navController.navigate(Destinations.Game.screen)
                                    onEvent(MainEvent.SetGameMode(GameMode.VsCPU))
                                }
                                "Online" -> {
                                    navController.navigate(Destinations.Game.screen)
                                    onEvent(MainEvent.SetGameMode(GameMode.Online))
                                }
                                "Settings" -> navController.navigate(Destinations.Settings.screen)
                            }
                        },
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = "Icon",
                            tint = screen.tint,
                            modifier = Modifier
                                .size(35.dp)
                                .padding(end = 3.dp),
                        )
                        Text(
                            modifier = Modifier.wrapContentSize(),
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontSize = 20.sp,
                            ),
                            color = MaterialTheme.colorScheme.onSurface,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            softWrap = true,
                            text = stringResource(screen.text),
                        )
                    }
                }
                if (index <= items.size - 1) {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }

    AnimatedVisibility(state.showWhatsNewDialog) {
        WhatsNewDialog(
            settingsVM::onEvent,
        )
    }

    // This permission should not be shown to the end user, it is only for test
    // cases where there is a crash in the application in the release version in which
    // the developer needs the log file that is generated in the internal storage
    if (showStoragePermissinRequest) {
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            0,
        )
    }

    BackHandler {
        settingsVM.onEvent(SettingsEvent.IsBGMusicPlaying(false))
        activity.finish()
        context.stopService(serviceIntent)
    }
}
