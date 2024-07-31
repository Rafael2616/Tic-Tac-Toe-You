package com.rafael.tictactoe.feature.settings.presentation.components.groups

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.group_others
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.AboutAppPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.OpenSourceLicensesPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.UpdateHistoryPreference
import com.rafael.tictactoe.feature.settings.presentation.components.preferences.ViewOnGithubPreference
import com.rafael.tictactoe.feature.settings.presentation.components.templates.PreferenceGroup

@Composable
fun OthersPreferenceGroup(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
    navController: NavController,
) {
    PreferenceGroup(heading = Res.string.group_others) {
        AboutAppPreference(onEvent, state)
        ViewOnGithubPreference()
        UpdateHistoryPreference(navController)
        OpenSourceLicensesPreference(navController)
    }
}
