package com.rafael.tictactoe.settings.domain.util

import com.rafael.tictactoe.R
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.*


@Composable
fun HandleCardText(
index: Int,
isAboutShown: Boolean
): String {
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