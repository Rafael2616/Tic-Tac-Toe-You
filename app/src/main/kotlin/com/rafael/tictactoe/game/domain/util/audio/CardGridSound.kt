package com.rafael.tictactoe.game.domain.util

import android.content.Context
import android.net.Uri
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.MediaItem
import com.rafael.tictactoe.R

fun CardGridSound(
	context: Context,
	isPlayer1: Boolean,
	isSoundEffectsOn: Boolean,
	isCardClicked: List<Boolean>,
	index: Int
) {

    val exoPlayer = ExoPlayer.Builder(context).build()
	val clickSoundFile = R.raw.click1_sound
	val clickSound2File = R.raw.click2_sound
    val clickSound1 = MediaItem.fromUri(Uri.parse("android.resource://$context/$clickSoundFile"))
    val clickSound2 = MediaItem.fromUri(Uri.parse("android.resource://$context/$clickSound2File"))

	if (isSoundEffectsOn) {
		if (!isCardClicked[index]) {
			if (isPlayer1) {
				exoPlayer.stop()
				exoPlayer.addMediaItem(clickSound1)
				exoPlayer.prepare()
				exoPlayer.play()
			} else {
				exoPlayer.stop()
				exoPlayer.addMediaItem(clickSound2)
				exoPlayer.prepare()
				exoPlayer.play()
			}
		} else {
			exoPlayer.release()
		}
	}
}