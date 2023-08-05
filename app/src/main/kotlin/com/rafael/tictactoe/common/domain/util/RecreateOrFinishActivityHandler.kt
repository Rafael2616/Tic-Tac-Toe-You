package com.rafael.tictactoe.common.domain.util

import android.app.Activity
import android.content.Context
import android.content.Intent
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.game.presentation.main_screen.activities.MainActivity

fun RecreateOrFinishActivityHandler(
	context: Context,
	activity: Activity,
	viewModel: SettingsViewModel,
	isActivityFinished: Boolean
) {

	if (isActivityFinished) {
		if(activity.isTaskRoot) {
    		context.startActivity(Intent(context, MainActivity::class.java))
		} else {
			activity.finish()
		}
		viewModel.updateIsActivityFinished(true)
	}
}