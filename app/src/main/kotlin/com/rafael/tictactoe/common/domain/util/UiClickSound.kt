package com.rafael.tictactoe.common.domain.util

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.R

fun UiClickSound(context: Context, exoPlayer: ExoPlayer) {

	val uiSoundFile = R.raw.ui_click_sound
    val uiSound = MediaItem.fromUri(Uri.parse("android.resource://$context/$uiSoundFile"))
	exoPlayer.addMediaItem(uiSound)
	exoPlayer.prepare()
    exoPlayer.setMediaItem(uiSound)
	exoPlayer.play()
    exoPlayer.removeMediaItem(R.raw.ui_click_sound)
}