/*
*This function is needed to avoid unecessary setContent metod call when the value
* of DarkTheme toogle is same of SystemDarkTheme and SystemTheme toogle is toogled
*/

package com.rafael.tictactoe.settings.domain.util

import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun SetContentDecider(
	viewModel: SettingsViewModel,
	isDarkThemeOn: Boolean,
	isSystemInDarkTheme: Boolean
) {

	if (((isSystemInDarkTheme) && (!isDarkThemeOn)) ||
	((!isSystemInDarkTheme) && (isDarkThemeOn))) {
		viewModel.updateIsContentSetted(false)
	}
}