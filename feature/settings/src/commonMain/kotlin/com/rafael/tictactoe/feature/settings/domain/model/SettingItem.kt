package com.rafael.tictactoe.feature.settings.domain.model

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import com.rafael.tictactoe.core.platform.presentation.dynamicColorsAvailability
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.about_app
import com.rafael.tictactoe.core.resources.background_music
import com.rafael.tictactoe.core.resources.board_customization
import com.rafael.tictactoe.core.resources.color_circle
import com.rafael.tictactoe.core.resources.color_x
import com.rafael.tictactoe.core.resources.dark_theme
import com.rafael.tictactoe.core.resources.material_you
import com.rafael.tictactoe.core.resources.open_source_licenses
import com.rafael.tictactoe.core.resources.sound_effects
import com.rafael.tictactoe.core.resources.system_theme
import com.rafael.tictactoe.core.resources.update_history
import com.rafael.tictactoe.core.resources.view_on_github
import com.rafael.tictactoe.core.resources.winner_line_color
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.AboutAppPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.BackgroundMusicPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.BoardCustomizationPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.CircleColorPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.DarkThemePreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.DynamicColorsPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.FollowSystemPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.OpenSourceLicensesPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.SoundEffectsPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.UpdateHistoryPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.ViewOnGithubPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.WinnerLineColorPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.XColorPreference
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import org.jetbrains.compose.resources.getString

@Stable
data class SettingItem(
    val content: (@Composable ((SettingsEvent) -> Unit, SettingsState, NavController) -> Unit)? = null,
    val searchSpecs: SearchItemSpecs,
    val isAvailable: Boolean = true,
) {
    companion object {
        private const val NAMESPACE = "settings"

        @OptIn(ExperimentalUuidApi::class)
        @Stable
        suspend fun getSettingsItems(
            settingsState: SettingsState,
        ): PersistentList<SettingItem> {
            return persistentListOf(
                // Follow System
                SettingItem(
                    content = { onEvent, state, _ -> FollowSystemPreference(onEvent, state) },
                    searchSpecs = SearchItemSpecs(
                        namespace = NAMESPACE,
                        id = Uuid.random().toString(),
                        text = getString(Res.string.system_theme),
                    ),
                ),
                // Dark Theme
                SettingItem(
                    content = { onEvent, state, _ -> DarkThemePreference(onEvent, state) },
                    searchSpecs = SearchItemSpecs(
                        namespace = NAMESPACE,
                        id = Uuid.random().toString(),
                        text = getString(Res.string.dark_theme),
                    ),
                    isAvailable = !settingsState.isFollowingSystemOn,

                ),
                // Dynamic Colors
                SettingItem(
                    content = { onEvent, state, _ -> DynamicColorsPreference(onEvent, state) },
                    searchSpecs = SearchItemSpecs(
                        namespace = NAMESPACE,
                        id = Uuid.random().toString(),
                        text = getString(Res.string.material_you),
                    ),
                    isAvailable = dynamicColorsAvailability,
                ),
                // Sound Effects
                SettingItem(
                    content = { onEvent, state, _ -> SoundEffectsPreference(onEvent, state) },
                    searchSpecs = SearchItemSpecs(
                        namespace = NAMESPACE,
                        id = Uuid.random().toString(),
                        text = getString(Res.string.sound_effects),
                    ),
                ),
                // Background Music
                SettingItem(
                    content = { onEvent, state, _ -> BackgroundMusicPreference(onEvent, state) },
                    searchSpecs = SearchItemSpecs(
                        namespace = NAMESPACE,
                        id = Uuid.random().toString(),
                        text = getString(Res.string.background_music),
                    ),
                ),
                // Winner Line Color
                SettingItem(
                    content = { onEvent, state, _ -> WinnerLineColorPreference(onEvent, state) },
                    searchSpecs = SearchItemSpecs(
                        namespace = NAMESPACE,
                        id = Uuid.random().toString(),
                        text = getString(Res.string.winner_line_color),
                    ),
                ),
                // Circle Color
                SettingItem(
                    content = { onEvent, state, _ -> CircleColorPreference(onEvent, state) },
                    searchSpecs = SearchItemSpecs(
                        namespace = NAMESPACE,
                        id = Uuid.random().toString(),
                        text = getString(Res.string.color_circle),
                    ),
                ),
                // X Color
                SettingItem(
                    content = { onEvent, state, _ -> XColorPreference(onEvent, state) },
                    searchSpecs = SearchItemSpecs(
                        namespace = NAMESPACE,
                        id = Uuid.random().toString(),
                        text = getString(Res.string.color_x),
                    ),
                ),
                // Board Cards
                SettingItem(
                    content = { onEvent, state, _ -> BoardCustomizationPreference(onEvent, state) },
                    searchSpecs = SearchItemSpecs(
                        namespace = NAMESPACE,
                        id = Uuid.random().toString(),
                        text = getString(Res.string.board_customization),
                    ),
                ),
                // About App
                SettingItem(
                    content = { onEvent, state, _ -> AboutAppPreference(onEvent, state) },
                    searchSpecs = SearchItemSpecs(
                        namespace = NAMESPACE,
                        id = Uuid.random().toString(),
                        text = getString(Res.string.about_app),
                    ),
                ),
                // Github repo
                SettingItem(
                    content = { _, _, _ -> ViewOnGithubPreference() },
                    searchSpecs = SearchItemSpecs(
                        namespace = NAMESPACE,
                        id = Uuid.random().toString(),
                        text = getString(Res.string.view_on_github),
                    ),
                ),
                // Update History
                SettingItem(
                    content = { _, _, navController -> UpdateHistoryPreference(navController) },
                    searchSpecs = SearchItemSpecs(
                        namespace = NAMESPACE,
                        id = Uuid.random().toString(),
                        text = getString(Res.string.update_history),
                    ),
                ),
                // Licences Screen
                SettingItem(
                    content = { _, _, navController -> OpenSourceLicensesPreference(navController) },
                    searchSpecs = SearchItemSpecs(
                        namespace = NAMESPACE,
                        id = Uuid.random().toString(),
                        text = getString(Res.string.open_source_licenses),
                    ),
                ),
            )
        }
    }
}
