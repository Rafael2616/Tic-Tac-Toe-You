package com.rafael.tictactoe.settings.presentation.settingsScreen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.VolumeOff
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.BrightnessMedium
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.MusicOff
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.icons.material.Github
import com.rafael.tictactoe.settings.domain.enums.SettingsGroup
import com.rafael.tictactoe.settings.domain.model.SettingItem
import com.rafael.tictactoe.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.domain.model.SettingsState
import kotlinx.collections.immutable.toPersistentList

@Composable
fun SettingsOptionsImpl(
    livePadding: PaddingValues,
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
    modifier: Modifier = Modifier,
) {
    val themeSettings = mutableStateListOf(
        // Follow System
        SettingItem(
            leadingIcon = Icons.Outlined.BrightnessMedium,
            text = R.string.system_theme,
            isChecked = state.isFollowingSystemOn,
        ),
        // Dark Theme
        SettingItem(
            leadingIcon = if (state.isDarkThemeOn) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
            text = R.string.dark_theme,
            isChecked = state.isDarkThemeOn,
        ),
        // Dynamic Colors
        SettingItem(
            leadingIcon = Icons.Outlined.Palette,
            text = R.string.material_you,
            isChecked = state.isDynamicColorsOn,
        ),
    )

    val soundSettings = mutableStateListOf(
        // Sound Effects
        SettingItem(
            leadingIcon = if (state.isSoundEffectsOn) Icons.Outlined.MusicNote else Icons.Outlined.MusicOff,
            text = R.string.sound_effects,
            isChecked = state.isSoundEffectsOn,
        ),
        // Background Music
        SettingItem(
            leadingIcon = if (state.isBackgroundMusicOn) Icons.AutoMirrored.Outlined.VolumeUp else Icons.AutoMirrored.Outlined.VolumeOff,
            text = R.string.background_music,
            isChecked = state.isBackgroundMusicOn,
        ),
    )

    val customizationSettings = mutableStateListOf(
        // Winner Line Color
        SettingItem(
            text = R.string.winner_line_color,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
        ),
        // Circle Color
        SettingItem(
            text = R.string.color_circle,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
        ),
        // X Color
        SettingItem(
            text = R.string.color_x,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
        ),
    )

    val otherSettings = mutableStateListOf(
        // About App
        SettingItem(
            leadingIcon = Icons.Outlined.Info,
            text = if (!state.isAboutShown) R.string.about_app else R.string.creator_message,
            trailingIcon = if (state.isAboutShown) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
        ),
        // Github repo
        SettingItem(
            leadingIcon = Icons.Rounded.Github,
            text = R.string.view_on_github,
            trailingIcon = Icons.Filled.ArrowOutward,
        ),
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(livePadding)
            .animateContentSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(SettingsGroup.values()) { group ->
            when (group) {
                SettingsGroup.Theme -> SettingsBlockWithSwitch(
                    state = state,
                    onEvent = onEvent,
                    groupType = SettingsGroup.Theme,
                    groupName = R.string.group_theme,
                    settings = themeSettings.toPersistentList(),
                )
                SettingsGroup.Sound -> SettingsBlockWithSwitch(
                    state = state,
                    onEvent = onEvent,
                    groupType = SettingsGroup.Sound,
                    groupName = R.string.group_sound,
                    settings = soundSettings.toPersistentList(),
                )
                SettingsGroup.Customization -> SettingsBlockWithIcon(
                    state = state,
                    onEvent = onEvent,
                    groupType = SettingsGroup.Customization,
                    groupName = R.string.group_customization,
                    settings = customizationSettings.toPersistentList(),
                )
                SettingsGroup.Others -> SettingsBlockWithIcon(
                    state = state,
                    onEvent = onEvent,
                    groupType = SettingsGroup.Others,
                    groupName = R.string.group_others,
                    settings = otherSettings.toPersistentList(),
                )
            }
        }
    }
}
