package com.rafael.tictactoe.common.domain.util

import android.content.Context
import android.net.Uri
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.MediaItem
import com.rafael.tictactoe.R

fun UiClickSound(context: Context, isSoundEffectsOn: Boolean) {

    val exoPlayer = ExoPlayer.Builder(context).build()
	val uiSoundFile = R.raw.ui_click_sound
    val uiSound = MediaItem.fromUri(Uri.parse("android.resource://$context/$uiSoundFile"))

	if (isSoundEffectsOn){
		exoPlayer.addMediaItem(uiSound)
		exoPlayer.prepare()
		exoPlayer.play()
	} else {
		//Do nothing
	}
}