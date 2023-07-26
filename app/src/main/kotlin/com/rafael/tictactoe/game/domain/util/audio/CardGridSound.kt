package com.rafael.tictactoe.game.domain.util

import android.content.Context
import android.media.MediaPlayer
import com.rafael.tictactoe.R

fun CardGridSound(
	context: Context,
	isPlayer1: Boolean,
	isSoundEffectsOn: Boolean,
	isCardClicked: List<Boolean>,
	index: Int
) {

    val clickSound1 = MediaPlayer.create(context, R.raw.click1_sound)
    val clickSound2 = MediaPlayer.create(context, R.raw.click2_sound)

	if ((isSoundEffectsOn) && (!isCardClicked[index])) {
		if (isPlayer1) {
			clickSound2.stop()
			clickSound1.start()
		} else {
			clickSound1.stop()
			clickSound2.start()
		}
	}
}