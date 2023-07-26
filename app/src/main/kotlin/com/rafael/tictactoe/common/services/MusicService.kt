package com.rafael.tictactoe.common.services

import android.os.IBinder
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.common.MediaItem
import com.rafael.tictactoe.R
import dagger.hilt.android.AndroidEntryPoint
import kotlin.lazy

@AndroidEntryPoint
class MusicService : Service() {

    private val exoPlayer: ExoPlayer by lazy { ExoPlayer.Builder(this).build() }
	val backgroundMusicFile = R.raw.background_music
    val backgroundMusic = MediaItem.fromUri(Uri.parse("android.resource://this/$backgroundMusicFile"))

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
		startBackgroundMusic()
    	return START_STICKY
    }
	
	fun startBackgroundMusic(){
		exoPlayer.addMediaItem(backgroundMusic)
    	exoPlayer.prepare()
		exoPlayer.setRepeatMode(ExoPlayer.REPEAT_MODE_ALL)
		exoPlayer.play()
	}
	
    override fun onDestroy() {
        super.onDestroy()
		exoPlayer.stop()
		exoPlayer.release()
    }
}