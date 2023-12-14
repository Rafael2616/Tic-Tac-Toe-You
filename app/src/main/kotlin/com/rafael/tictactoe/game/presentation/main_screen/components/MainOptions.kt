package com.rafael.tictactoe.game.presentation.mainScreen.components

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.domain.model.ScreenItem
import com.rafael.tictactoe.game.presentation.gameScreen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settingsScreen.viewmodels.SettingsViewModel

@Composable
fun MainOptions(
    navController: NavController,
    settingsVM: SettingsViewModel,
    viewModel: GameViewModel,
    modifier: Modifier = Modifier,
) {
    val orientation = LocalConfiguration.current.orientation
    val state by settingsVM.state.collectAsState()
    val onEvent = viewModel::onEvent

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
            .padding(top = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 45.dp else 0.dp),
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
                    .fillMaxWidth(if (orientation == Configuration.ORIENTATION_LANDSCAPE) 0.3f else 0.55f)
                    .height(55.dp)
                    .clip(RoundedCornerShape(36.dp))
                    .clickable {
                        settingsVM.execUIClickSound()
                        when (screen.name) {
                            "PvP" -> {
                                navController.navigate(Destinations.Game.screen + "/false")
                                onEvent(GameEvent.SetGameMode(GameMode.PvP))
                            }
                            "CPU" -> {
                                navController.navigate(Destinations.Game.screen + "/true")
                                onEvent(GameEvent.SetGameMode(GameMode.VsCPU))
                            }
                            "Online" -> {
                                navController.navigate(Destinations.Game.screen + "/false")
                                onEvent(GameEvent.SetGameMode(GameMode.Online))
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
                            .size(40.dp)
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
