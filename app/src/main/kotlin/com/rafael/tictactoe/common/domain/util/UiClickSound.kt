package com.rafael.tictactoe.common.domain.util

import android.content.*
import android.media.MediaPlayer
import com.rafael.tictactoe.R

fun UiClickSound(context: Context, isSoundEffectsOn: Boolean) {

    val uiSound = MediaPlayer.create(context, R.raw.ui_click_sound)
	if (isSoundEffectsOn){
		uiSound.start()
	} else {
		uiSound.stop()
	}
}