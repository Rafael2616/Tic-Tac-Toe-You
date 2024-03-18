package com.rafael.tictactoe.settings.settingsScreen.domain.model

import android.content.Context
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
import androidx.compose.ui.graphics.vector.ImageVector
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.icons.material.Github
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchItemSpecs
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.SettingsGroup
import java.util.UUID
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class SettingItem(
    val leadingIcon: ImageVector? = null,
    val trailingIcon: ImageVector? = null,
    val isChecked: Boolean? = null,
    val index: Int,
    val groupType: SettingsGroup,
    val searchSpecs: SearchItemSpecs,
) {
    companion object {
        fun getSettingsItems(
            state: SettingsState,
            context: Context,
        ): PersistentList<PersistentList<SettingItem>> {
            val searchItemSpec = SearchItemSpecs(
                namespace = "settings",
                id = "",
                text = "",
            )

            val themeSettings = persistentListOf(
                // Follow System
                SettingItem(
                    leadingIcon = Icons.Outlined.BrightnessMedium,
                    isChecked = state.isFollowingSystemOn,
                    groupType = SettingsGroup.Theme,
                    index = 0,
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.system_theme),
                    ),
                ),
                // Dark Theme
                SettingItem(
                    leadingIcon = if (state.isDarkThemeOn) Icons.Outlined.DarkMode else Icons.Outlined.LightMode,
                    isChecked = state.isDarkThemeOn,
                    groupType = SettingsGroup.Theme,
                    index = 1,
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.dark_theme),
                    ),

                ),
                // Dynamic Colors
                SettingItem(
                    leadingIcon = Icons.Outlined.Palette,
                    isChecked = state.isDynamicColorsOn,
                    groupType = SettingsGroup.Theme,
                    index = 2,
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.material_you),
                    ),
                ),
            )

            val soundSettings = persistentListOf(
                // Sound Effects
                SettingItem(
                    leadingIcon = if (state.isSoundEffectsOn) Icons.Outlined.MusicNote else Icons.Outlined.MusicOff,
                    isChecked = state.isSoundEffectsOn,
                    groupType = SettingsGroup.Sound,
                    index = 0,
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.sound_effects),
                    ),
                ),
                // Background Music
                SettingItem(
                    leadingIcon = if (state.isBackgroundMusicOn) Icons.AutoMirrored.Outlined.VolumeUp else Icons.AutoMirrored.Outlined.VolumeOff,
                    isChecked = state.isBackgroundMusicOn,
                    groupType = SettingsGroup.Sound,
                    index = 1,
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.background_music),
                    ),
                ),
            )

            val customizationSettings = persistentListOf(
                // Winner Line Color
                SettingItem(
                    trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
                    groupType = SettingsGroup.Customization,
                    index = 0,
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.winner_line_color),
                    ),
                ),
                // Circle Color
                SettingItem(
                    trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
                    groupType = SettingsGroup.Customization,
                    index = 1,
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.color_circle),
                    ),
                ),
                // X Color
                SettingItem(
                    trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
                    groupType = SettingsGroup.Customization,
                    index = 2,
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.color_x),
                    ),
                ),
                // Board Cards
                SettingItem(
                    trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
                    groupType = SettingsGroup.Customization,
                    index = 3,
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.board_customization),
                    ),
                ),
            )

            val otherSettings = persistentListOf(
                // About App
                SettingItem(
                    leadingIcon = Icons.Outlined.Info,
                    trailingIcon = if (state.isAboutShown) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    groupType = SettingsGroup.Others,
                    index = 0,
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.about_app),
                    ),
                ),
                // Github repo
                SettingItem(
                    leadingIcon = Icons.Rounded.Github,
                    trailingIcon = Icons.Filled.ArrowOutward,
                    groupType = SettingsGroup.Others,
                    index = 1,
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.view_on_github),
                    ),
                ),
                // What's New
                SettingItem(
                    leadingIcon = Icons.Outlined.NewReleases,
                    trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
                    groupType = SettingsGroup.Others,
                    index = 2,
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.whats_new),
                    ),
                ),
                // Licences Screen
                SettingItem(
                    leadingIcon = Icons.Outlined.Code,
                    trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
                    groupType = SettingsGroup.Others,
                    index = 3,
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.open_source_licenses),
                    ),
                ),
            )
            val settings = persistentListOf(
                themeSettings,
                soundSettings,
                customizationSettings,
                otherSettings,
            )

            return settings
        }
    }
}
