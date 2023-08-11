package com.rafael.tictactoe.settings.domain.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun CardTextHandler(index: Int): String {
    val viewModel = viewModel<SettingsViewModel>()
    val isAboutShown by viewModel.isAboutShown.collectAsState()

    return when (index) {
        0 -> stringResource(R.string.system_theme)
        1 -> stringResource(R.string.dark_theme)
        2 -> stringResource(R.string.material_you)
        3 -> stringResource(R.string.sound_effects)
        4 -> stringResource(R.string.background_music)
        5 -> stringResource(id = if (!isAboutShown) R.string.about_app else R.string.creator_message)
     else -> stringResource(R.string.index_error)
    }
}