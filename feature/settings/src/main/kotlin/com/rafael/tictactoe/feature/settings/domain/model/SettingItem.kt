package com.rafael.tictactoe.settings.settingsScreen.domain.model

import android.content.Context
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import com.rafael.tictactoe.feature.settings.R
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchItemSpecs
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.AboutAppPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.BackgroundMusicPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.BoardCustomizationPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.CircleColorPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.DarkThemePreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.DynamicColorsPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.FollowSystemPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.OpenSourceLicensesPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.SoundEffectsPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.UpdateHistoryPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.ViewOnGithubPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.WinnerLineColorPreference
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences.XColorPreference
import java.util.UUID
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class SettingItem(
    val content: (@Composable ((SettingsEvent) -> Unit, SettingsState, NavController) -> Unit)? = null,
    val searchSpecs: SearchItemSpecs,
    val isAvailable: Boolean = true,
) {
    companion object {
        @Stable
        fun getSettingsItems(
            context: Context,
            settingsState: SettingsState,
        ): PersistentList<SettingItem> {
            val searchItemSpec = SearchItemSpecs(
                namespace = "settings",
                id = "",
                text = "",
            )

            return persistentListOf(
                // Follow System
                SettingItem(
                    content = { onEvent, state, _ -> FollowSystemPreference(onEvent, state) },
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.system_theme),
                    ),
                ),
                // Dark Theme
                SettingItem(
                    content = { onEvent, state, _ -> DarkThemePreference(onEvent, state) },
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.dark_theme),
                    ),
                    isAvailable = !settingsState.isFollowingSystemOn,

                ),
                // Dynamic Colors
                SettingItem(
                    content = { onEvent, state, _ -> DynamicColorsPreference(onEvent, state) },
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.material_you),
                    ),
                    isAvailable = VERSION.SDK_INT >= VERSION_CODES.S,
                ),
                // Sound Effects
                SettingItem(
                    content = { onEvent, state, _ -> SoundEffectsPreference(onEvent, state) },
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.sound_effects),
                    ),
                ),
                // Background Music
                SettingItem(
                    content = { onEvent, state, _ -> BackgroundMusicPreference(onEvent, state) },
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.background_music),
                    ),
                ),
                // Winner Line Color
                SettingItem(
                    content = { onEvent, state, _ -> WinnerLineColorPreference(onEvent, state) },
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.winner_line_color),
                    ),
                ),
                // Circle Color
                SettingItem(
                    content = { onEvent, state, _ -> CircleColorPreference(onEvent, state) },
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.color_circle),
                    ),
                ),
                // X Color
                SettingItem(
                    content = { onEvent, state, _ -> XColorPreference(onEvent, state) },
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.color_x),
                    ),
                ),
                // Board Cards
                SettingItem(
                    content = { onEvent, state, _ -> BoardCustomizationPreference(onEvent, state) },
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.board_customization),
                    ),
                ),
                // About App
                SettingItem(
                    content = { onEvent, state, _ -> AboutAppPreference(onEvent, state) },
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.about_app),
                    ),
                ),
                // Github repo
                SettingItem(
                    content = { _, _, _ -> ViewOnGithubPreference() },
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.view_on_github),
                    ),
                ),
                // Update History
                SettingItem(
                    content = { _, _, navController -> UpdateHistoryPreference(navController) },
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.update_history),
                    ),
                ),
                // Licences Screen
                SettingItem(
                    content = { _, _, navController -> OpenSourceLicensesPreference(navController) },
                    searchSpecs = searchItemSpec.copy(
                        id = UUID.randomUUID().toString(),
                        text = context.getString(R.string.open_source_licenses),
                    ),
                ),
            )
        }
    }
}
