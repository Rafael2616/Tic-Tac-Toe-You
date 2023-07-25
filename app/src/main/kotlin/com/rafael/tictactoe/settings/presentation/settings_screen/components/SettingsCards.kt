package com.rafael.tictactoe.settings.presentation.settings_screen.components

import com.rafael.tictactoe.R
import android.content.Context
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import com.rafael.tictactoe.settings.domain.util.CardImageHandler
import com.rafael.tictactoe.settings.domain.util.CardTextHandler
import com.rafael.tictactoe.settings.domain.util.SwitchValuesHandler
import com.rafael.tictactoe.settings.domain.util.SwitchOnCheckedChangeHandler
import com.rafael.tictactoe.settings.domain.util.SyncDarkThemeToggleValue
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.common.domain.util.UiClickSound

@Composable
fun SettingsCards(
    context: Context,
    index: Int,
    viewModel: SettingsViewModel
) {

    val isSystemInDarkTheme = isSystemInDarkTheme()
    val isDarkThemeOn by viewModel.isDarkThemeOn.collectAsState()
    val isFollowingSystemOn by viewModel.isFollowingSystemOn.collectAsState()
    val isDynamicColorsOn by viewModel.isDynamicColorsOn.collectAsState()
    val isBackgroundMusicOn by viewModel.isBackgroundMusicOn.collectAsState()
    val isSoundEffectsOn by viewModel.isSoundEffectsOn.collectAsState()
    val isAboutShown by viewModel.isAboutShown.collectAsState()

    SyncDarkThemeToggleValue(viewModel, isFollowingSystemOn, isSystemInDarkTheme)

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .width(360.dp)
            .height(if ((index == 5) && (isAboutShown)) 190.dp else 70.dp)
            .clickable(onClick = {
                SwitchOnCheckedChangeHandler(
                    context,
                    index,
                    viewModel,
                    isFollowingSystemOn,
                    isDarkThemeOn,
                    isDynamicColorsOn,
                    isSoundEffectsOn,
                    isBackgroundMusicOn,
                    isAboutShown
                )
                UiClickSound(context, isSoundEffectsOn)
            })
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    contentDescription = null,
                    modifier = Modifier
                        .height(55.dp)
                        .width(55.dp),
                    painter = CardImageHandler(index, viewModel)
                )
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = CardTextHandler(index, viewModel)
                )
                if (index != 5) {
                    Switch(
                        checked = SwitchValuesHandler(
                            index,
                            isFollowingSystemOn,
                            isDarkThemeOn,
                            isDynamicColorsOn,
                            isSoundEffectsOn,
                            isBackgroundMusicOn,
                            isAboutShown
                        ),
                        onCheckedChange = {
                            SwitchOnCheckedChangeHandler(
                                context,
                                index,
                                viewModel,
                                isFollowingSystemOn,
                                isDarkThemeOn,
                                isDynamicColorsOn,
                                isSoundEffectsOn,
                                isBackgroundMusicOn,
                                isAboutShown
                            )
                            UiClickSound(context, isSoundEffectsOn)
                        }
                    )
                }
                if (index == 5) {
                    Image(
                        contentDescription = null,
                        modifier = Modifier
                            .height(35.dp)
                            .width(45.dp),
                        painter = painterResource(if (!isAboutShown) R.drawable.expand_down_icon else R.drawable.expand_up_icon)
                    )
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}
