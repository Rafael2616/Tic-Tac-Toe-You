package com.rafael.tictactoe.feature.main.presentation

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rafael.tictactoe.core.game.domain.model.GameMode
import com.rafael.tictactoe.core.navigation.Destinations
import com.rafael.tictactoe.core.navigation.Game
import com.rafael.tictactoe.core.navigation.Settings
import com.rafael.tictactoe.core.platform.configuration.isInLandscape
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.inapp_title
import com.rafael.tictactoe.core.resources.online_match
import com.rafael.tictactoe.core.resources.play
import com.rafael.tictactoe.core.resources.settings
import com.rafael.tictactoe.core.resources.vs_cpu
import com.rafael.tictactoe.core.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.core.settings.domain.model.SettingsState
import com.rafael.tictactoe.core.ui.theme.AppTheme
import com.rafael.tictactoe.feature.changelogs.domain.Changelog
import com.rafael.tictactoe.feature.changelogs.presentation.components.WhatsNewDialog
import com.rafael.tictactoe.feature.main.domain.model.MainState
import com.rafael.tictactoe.feature.main.domain.model.ScreenItem
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(
    navController: NavController,
    state: SettingsState,
    mainState: MainState,
    onRedirectToGameModes: (Boolean) -> Unit,
    onSettingsEvent: (SettingsEvent) -> Unit,
    modifier: Modifier = Modifier,
    currentChangelog: Changelog? = null,
    destination: Destinations? = null,
) {
    val items = listOf(
        ScreenItem(
            icon = Icons.Outlined.PlayArrow,
            tint = if (state.isDarkThemeOn) Color.Green else Color(0xFF299961),
            text = Res.string.play,
            onClick = { navController.navigate(Game(GameMode.PvP)) },
        ),
        ScreenItem(
            icon = Icons.Outlined.Memory,
            tint = Color.Red,
            text = Res.string.vs_cpu,
            onClick = { navController.navigate(Game(GameMode.VsCPU)) },
        ),
        ScreenItem(
            icon = Icons.Outlined.Group,
            tint = MaterialTheme.colorScheme.onSurface,
            text = Res.string.online_match,
            onClick = { navController.navigate(Game(GameMode.Online)) },
        ),
        ScreenItem(
            icon = Icons.Outlined.Settings,
            tint = MaterialTheme.colorScheme.onSurface,
            text = Res.string.settings,
            onClick = { navController.navigate(Settings) },
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
            text = stringResource(Res.string.inapp_title),
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
                .padding(top = if (isInLandscape) 35.dp else 0.dp),
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
                        .fillMaxWidth(if (isInLandscape) 0.33f else 0.6f)
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
        currentChangelog?.let {
            WhatsNewDialog(
                currentChangelog = it,
                onEvent = onSettingsEvent,
                navController = navController,
            )
        }
    }

    /**
     * Redirect user to property game mode screen when he presses
     * the long click app shortcuts
     **/
    if (mainState.canRedirectToAnGameModeScreen && destination != null) {
        navController.navigate(destination)
        onRedirectToGameModes(false)
    }
}

@Preview
// @PreviewLightDark
// @PreviewScreenSizes
@Composable
private fun MainScreenPreview() {
    AppTheme {
        MainScreen(
            navController = rememberNavController(),
            state = SettingsState(),
            mainState = MainState(),
            onRedirectToGameModes = {},
            onSettingsEvent = {},
        )
    }
}
