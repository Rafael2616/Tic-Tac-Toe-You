package com.rafael.tictactoe.common.domain.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.rafael.tictactoe.game.presentation.main_screen.activities.MainActivity
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

fun RecreateOrFinishActivityHandler(
	context: Context,
	activity: Activity,
	viewModel: SettingsViewModel
) {

	if (!viewModel.isActivityFinished.value) {
		if(activity.isTaskRoot) {
    		context.startActivity(Intent(context, MainActivity::class.java))
		} else {
			activity.finish()
		}
		viewModel.updateIsActivityFinished(true)
	}
}