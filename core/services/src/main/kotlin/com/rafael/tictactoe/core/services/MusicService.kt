package com.rafael.tictactoe.core.services

import android.app.Service
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MusicService @Inject constructor() : Service() {

    private lateinit var exoPlayer: ExoPlayer
    val backgroundMusicFile = R.raw.background_music
    val backgroundMusic = MediaItem.fromUri(Uri.parse("android.resource://this/$backgroundMusicFile"))

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        startBackgroundMusic()
        return START_STICKY
    }

    fun startBackgroundMusic() {
        exoPlayer = ExoPlayer.Builder(this).build()
        exoPlayer.addMediaItem(backgroundMusic)
        exoPlayer.prepare()
        exoPlayer.setRepeatMode(ExoPlayer.REPEAT_MODE_ALL)
        exoPlayer.play()
    }

    override fun onDestroy() {
        exoPlayer.stop()
        exoPlayer.release()
        super.onDestroy()
    }
}
