package com.rafael.tictactoe.common.domain.util

import android.content.*
import android.media.MediaPlayer
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.runBlocking


fun UiClickSound(
context: Context,
settingsRepo: SettingsRepository
) = runBlocking {
	val settings = settingsRepo.getAllSettingsValues()
    val isSoundEffectsOn = settings.isBackgroundMusicOn
    val uiSound = MediaPlayer.create(context, R.raw.ui_click_sound)

if (isSoundEffectsOn){
	uiSound.start()
} else {
	uiSound.stop()
	}
}