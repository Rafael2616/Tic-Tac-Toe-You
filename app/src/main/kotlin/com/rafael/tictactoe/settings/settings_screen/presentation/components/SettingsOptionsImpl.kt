package com.rafael.tictactoe.settings.settingsScreen.presentation.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.VolumeOff
import androidx.compose.material.icons.automirrored.outlined.VolumeUp
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.BrightnessMedium
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.LightMode
import androidx.compose.material.icons.outlined.MusicNote
import androidx.compose.material.icons.outlined.MusicOff
import androidx.compose.material.icons.outlined.NewReleases
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.icons.material.Github
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchSettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.SettingsGroup
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingItem
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingItemDoc
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import java.util.UUID
import kotlinx.collections.immutable.persistentListOf

@Composable
fun SettingsOptionsImpl(
    livePadding: PaddingValues,
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
    navController: NavController,
    onSeachEvent: (SearchSettingsEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val searchItemSpec = SettingItemDoc(
        namespace = "settings",
        id = "",
        text = "",
    )

    val themeSettings = persistentListOf(
        // Follow System
        SettingItem(
            leadingIcon = Icons.Outlined.BrightnessMedium,
            text = R.string.system_theme,
            isChecked = state.isFollowingSystemOn,
            groupType = SettingsGroup.Theme,
            searchText = searchItemSpec.copy(
                id = UUID.randomUUID().toString(),
                text = context.getString(R.string.system_theme),
            ),
        ),
        // Dark Theme
        SettingItem(
            leadingIcon = if (state.isDarkThemeOn) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
            text = R.string.dark_theme,
            isChecked = state.isDarkThemeOn,
            groupType = SettingsGroup.Theme,
            searchText = searchItemSpec.copy(
                id = UUID.randomUUID().toString(),
                text = context.getString(R.string.system_theme),
            ),

        ),
        // Dynamic Colors
        SettingItem(
            leadingIcon = Icons.Outlined.Palette,
            text = R.string.material_you,
            isChecked = state.isDynamicColorsOn,
            groupType = SettingsGroup.Theme,
            searchText = searchItemSpec.copy(
                id = UUID.randomUUID().toString(),
                text = context.getString(R.string.system_theme),
            ),
        ),
    )

    val soundSettings = persistentListOf(
        // Sound Effects
        SettingItem(
            leadingIcon = if (state.isSoundEffectsOn) Icons.Outlined.MusicNote else Icons.Outlined.MusicOff,
            text = R.string.sound_effects,
            isChecked = state.isSoundEffectsOn,
            groupType = SettingsGroup.Sound,
            searchText = searchItemSpec.copy(
                id = UUID.randomUUID().toString(),
                text = context.getString(R.string.system_theme),
            ),
        ),
        // Background Music
        SettingItem(
            leadingIcon = if (state.isBackgroundMusicOn) Icons.AutoMirrored.Outlined.VolumeUp else Icons.AutoMirrored.Outlined.VolumeOff,
            text = R.string.background_music,
            isChecked = state.isBackgroundMusicOn,
            groupType = SettingsGroup.Sound,
            searchText = searchItemSpec.copy(
                id = UUID.randomUUID().toString(),
                text = context.getString(R.string.system_theme),
            ),
        ),
    )

    val customizationSettings = persistentListOf(
        // Winner Line Color
        SettingItem(
            text = R.string.winner_line_color,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
            groupType = SettingsGroup.Customization,
            searchText = searchItemSpec.copy(
                id = UUID.randomUUID().toString(),
                text = context.getString(R.string.system_theme),
            ),
        ),
        // Circle Color
        SettingItem(
            text = R.string.color_circle,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
            groupType = SettingsGroup.Customization,
            searchText = searchItemSpec.copy(
                id = UUID.randomUUID().toString(),
                text = context.getString(R.string.system_theme),
            ),
        ),
        // X Color
        SettingItem(
            text = R.string.color_x,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
            groupType = SettingsGroup.Customization,
            searchText = searchItemSpec.copy(
                id = UUID.randomUUID().toString(),
                text = context.getString(R.string.system_theme),
            ),
        ),
    )

    val otherSettings = persistentListOf(
        // About App
        SettingItem(
            leadingIcon = Icons.Outlined.Info,
            text = if (!state.isAboutShown) R.string.about_app else R.string.creator_message,
            trailingIcon = if (state.isAboutShown) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            groupType = SettingsGroup.Others,
            searchText = searchItemSpec.copy(
                id = UUID.randomUUID().toString(),
                text = context.getString(R.string.system_theme),
            ),
        ),
        // Github repo
        SettingItem(
            leadingIcon = Icons.Rounded.Github,
            text = R.string.view_on_github,
            trailingIcon = Icons.Filled.ArrowOutward,
            groupType = SettingsGroup.Others,
            searchText = searchItemSpec.copy(
                id = UUID.randomUUID().toString(),
                text = context.getString(R.string.system_theme),
            ),
        ),
        // What's New
        SettingItem(
            leadingIcon = Icons.Outlined.NewReleases,
            text = R.string.whats_new,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
            groupType = SettingsGroup.Others,
            searchText = searchItemSpec.copy(
                id = UUID.randomUUID().toString(),
                text = context.getString(R.string.system_theme),
            ),
        ),
        // Licences Screen
        SettingItem(
            leadingIcon = Icons.Outlined.Code,
            text = R.string.open_source_licenses,
            trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
            groupType = SettingsGroup.Others,
            searchText = searchItemSpec.copy(
                id = UUID.randomUUID().toString(),
                text = context.getString(R.string.system_theme),
            ),
        ),
    )
    val settings = persistentListOf(
        themeSettings,
        soundSettings,
        customizationSettings,
        otherSettings,
    )

    var subItems: List<SettingItem> = emptyList()
    for (setting in settings) {
        for (subSettings in setting) {
            subItems += subSettings
            onSeachEvent(SearchSettingsEvent.PutSettingsToRepository(subItems))
        }
    }

    val lazyState = rememberLazyListState()
    LazyColumn(
        state = lazyState,
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
                    settings = themeSettings,
                )
                SettingsGroup.Sound -> SettingsBlockWithSwitch(
                    state = state,
                    onEvent = onEvent,
                    groupType = SettingsGroup.Sound,
                    groupName = R.string.group_sound,
                    settings = soundSettings,
                )
                SettingsGroup.Customization -> SettingsBlockWithIcon(
                    state = state,
                    onEvent = onEvent,
                    groupType = SettingsGroup.Customization,
                    groupName = R.string.group_customization,
                    settings = customizationSettings,
                    navController = navController,
                )
                SettingsGroup.Others -> SettingsBlockWithIcon(
                    state = state,
                    onEvent = onEvent,
                    groupType = SettingsGroup.Others,
                    groupName = R.string.group_others,
                    settings = otherSettings,
                    navController = navController,
                )
            }
        }
    }
}
