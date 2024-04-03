package com.rafael.tictactoe.settings.settingsScreen.domain.useCases

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.navigation.NavController
import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.Customization
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.SettingsGroup
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState

fun toggleSettingsUseCase(
    groupType: SettingsGroup,
    index: Int,
    state: SettingsState,
    onEvent: (SettingsEvent) -> Unit,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>? = null,
    navController: NavController? = null,
) {
    val githubRepoIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Rafael2616/Tic-Tac-Toe-You"))

    fun toggleThemeSettings(index: Int) {
        when (index) {
            0 -> onEvent(SettingsEvent.ToggleIsFollowingSystemOn)
            1 -> onEvent(SettingsEvent.ToggleIsDarkThemeOn)
            2 -> onEvent(SettingsEvent.ToggleIsDynamicColorsOn)
        }
    }

    fun toggleSoundSettings(index: Int) {
        when (index) {
            0 -> onEvent(SettingsEvent.ToggleIsSoundEffectsOn)
            1 -> onEvent(SettingsEvent.ToggleIsBackgroundMusicOn)
        }
    }

    fun toggleCustomizationSettings(index: Int) {
        when (index) {
            0 -> {
                onEvent(SettingsEvent.SetCustomizationType(Customization.WINNERLINECOLOR))
                onEvent(SettingsEvent.ShowColorSelectionDialog(true))
            }
            1 -> {
                onEvent(SettingsEvent.SetCustomizationType(Customization.CIRCLECOLOR))
                onEvent(SettingsEvent.ShowColorSelectionDialog(true))
            }
            2 -> {
                onEvent(SettingsEvent.SetCustomizationType(Customization.XCOLOR))
                onEvent(SettingsEvent.ShowColorSelectionDialog(true))
            }
            3 -> onEvent(SettingsEvent.ShowBoardCustomizationDialog(true))
        }
    }

    fun toggleOtherSettings(index: Int) {
        when (index) {
            0 -> onEvent(SettingsEvent.ShowAboutAppCard(!state.isAboutShown))
            1 -> launcher!!.launch(githubRepoIntent)
            2 -> navController!!.navigate(Destinations.Changelogs.screen)
            3 -> navController!!.navigate(Destinations.Library.screen)
        }
    }

    when (groupType) {
        SettingsGroup.Theme -> toggleThemeSettings(index)
        SettingsGroup.Sound -> toggleSoundSettings(index)
        SettingsGroup.Customization -> toggleCustomizationSettings(index)
        SettingsGroup.Others -> toggleOtherSettings(index)
    }
    onEvent(SettingsEvent.ExecUIClickSound)
}
