package com.rafael.tictactoe.game.presentation.main_screen.components

import android.content.res.Configuration
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Memory
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun MainOptions(navController: NavController) {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val orientation = LocalConfiguration.current.orientation
    val state by settingsVM.state.collectAsState()
    val viewModel = hiltViewModel<GameViewModel>()
    val onEvent = viewModel::onEvent

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 45.dp else 0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Screen.values().forEachIndexed { index, screen ->
            Card(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
                ),
                modifier = Modifier
                    .fillMaxWidth(if (orientation == Configuration.ORIENTATION_LANDSCAPE) 0.35f else 0.65f)
                    .height(65.dp)
                    .clip(RoundedCornerShape(36.dp))
                    .clickable {
                        settingsVM.execUIClickSound()
                        when (screen) {
                            Screen.PvP_Mode -> {
                                navController.navigate(Destinations.Game.screen + "/false")
                                onEvent(GameEvent.SetGameMode(GameMode.PvP))
                            }
                            Screen.CPU_Mode -> {
                                navController.navigate(Destinations.Game.screen + "/true")
                                onEvent(GameEvent.SetGameMode(GameMode.VsCPU))
                            }
                            Screen.Online_Mode -> {
                                navController.navigate(Destinations.Game.screen + "/false")
                                onEvent(GameEvent.SetGameMode(GameMode.Online))
                            }
                            Screen.Settings -> navController.navigate(Destinations.Settings.screen)
                        }
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 10.dp, start = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        imageVector = when (screen) {
                            Screen.PvP_Mode -> Icons.Outlined.PlayArrow
                            Screen.CPU_Mode -> Icons.Outlined.Memory
                            Screen.Online_Mode -> Icons.Outlined.Group
                            Screen.Settings -> Icons.Outlined.Settings
                        },
                        contentDescription = "Icon",
                        tint = when (screen) {
                            Screen.PvP_Mode -> if (state.isDarkThemeOn) Color.Green else Color(0xFF299961)
                            Screen.CPU_Mode -> Color.Red
                            Screen.Online_Mode -> MaterialTheme.colorScheme.onSurface
                            Screen.Settings -> MaterialTheme.colorScheme.onSurface
                        },
       		    	 modifier = Modifier
                            .size(42.dp)
                            .padding(end = 3.dp)
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        softWrap = true,
                        text = when (screen) {
                            Screen.PvP_Mode -> stringResource(R.string.play)
                            Screen.CPU_Mode -> stringResource(R.string.vs_cpu)
                            Screen.Online_Mode -> "Partida Online"
                            Screen.Settings -> stringResource(R.string.settings)
                        }
                    )
                }
            }
            if (index <= Screen.values().size - 1) {
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}

enum class Screen {
    PvP_Mode,
    CPU_Mode,
    Online_Mode,
    Settings
}