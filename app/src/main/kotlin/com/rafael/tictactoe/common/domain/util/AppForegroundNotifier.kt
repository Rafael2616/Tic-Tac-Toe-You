package com.rafael.tictactoe.common.domain.util

import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun AppForegroundNotifier(isAppInForeground: Boolean /*, viewModel: SettingsViewModel*/): Boolean {	
	//viewModel.setIsAppInForeground(isAppInForeground)
	return isAppInForeground
}