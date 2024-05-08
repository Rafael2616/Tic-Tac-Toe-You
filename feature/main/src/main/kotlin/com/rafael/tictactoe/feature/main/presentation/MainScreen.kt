package com.rafael.tictactoe.game.mainScreen.presentation

import android.content.Intent
import android.content.res.Configuration
import androidx.activity.ComponentActivity
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
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.core.resources.R
//import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.game.domain.model.GameMode
import com.rafael.tictactoe.game.mainScreen.domain.model.MainEvent
import com.rafael.tictactoe.game.mainScreen.domain.model.ScreenItem
import com.rafael.tictactoe.game.mainScreen.presentation.util.ShortcutsIntentHandler
import com.rafael.tictactoe.game.mainScreen.presentation.viewmodel.MainViewModel
import com.rafael.tictactoe.settings.changelogsScreen.presentation.components.WhatsNewDialog
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodel.SettingsViewModel

@Composable
@ExperimentalTextApi
fun MainScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    intent: Intent? = null,
    settingsVM: SettingsViewModel = hiltViewModel(),
    mainVM: MainViewModel = hiltViewModel(),
) {
    val activity = LocalContext.current as ComponentActivity
    val state by settingsVM.state.collectAsState()
    val mainState by mainVM.state.collectAsState()
    val onEvent = mainVM::onEvent
    val orientation = LocalConfiguration.current.orientation

    val items = listOf(
        ScreenItem(
            icon = Icons.Outlined.PlayArrow,
            tint = if (state.isDarkThemeOn) Color.Green else Color(0xFF299961),
            text = R.string.play,
            onClick = {
               // navController.navigate(Destinations.Game.screen)
                onEvent(MainEvent.SetGameMode(GameMode.PvP))
            },
        ),
        ScreenItem(
            icon = Icons.Outlined.Memory,
            tint = Color.Red,
            text = R.string.vs_cpu,
            onClick = {
               // navController.navigate(Destinations.Game.screen)
                onEvent(MainEvent.SetGameMode(GameMode.VsCPU))
            },
        ),
        ScreenItem(
            icon = Icons.Outlined.Group,
            tint = MaterialTheme.colorScheme.onSurface,
            text = R.string.online_match,
            onClick = {
               // navController.navigate(Destinations.Game.screen)
                onEvent(MainEvent.SetGameMode(GameMode.Online))
            },
        ),
        ScreenItem(
            icon = Icons.Outlined.Settings,
            tint = MaterialTheme.colorScheme.onSurface,
            text = R.string.settings,
            onClick = { /*navController.navigate(Destinations.Settings.screen) */},
        ),
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 10.dp, bottom = 10.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = R.string.inapp_title),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Spacer(
            modifier = Modifier.height(3.dp),
        )
        Text(
            text = "Material You",
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 18.sp),
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
                        .fillMaxWidth(if (orientation == Configuration.ORIENTATION_LANDSCAPE) 0.33f else 0.6f)
                        .height(58.dp)
                        .shadow(6.dp, RoundedCornerShape(36.dp))
                        .clip(RoundedCornerShape(36.dp))
                        .clickable { screen.onClick() },
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
                            style = MaterialTheme.typography.titleLarge.copy(fontSize = 20.sp),
                            color = MaterialTheme.colorScheme.onSurface,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center,
                            softWrap = true,
                            text = stringResource(screen.text),
                        )
                    }
                }
                if (index < (items.size - 1)) {
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }

    AnimatedVisibility(state.showWhatsNewDialog) {
        WhatsNewDialog(
            settingsVM::onEvent,
            navController,
        )
    }

    /**
     * Redirect user to property game mode screen when he presses
     * the long click app shortcuts
     **/
    if (mainState.canRedirectToAnGameModeScreen && intent != null) {
        ShortcutsIntentHandler(activity, navController, mainVM::onEvent, intent)
        mainVM.canRedirectToAnGameModeScreen(false)
    }
}
