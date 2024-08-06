package com.rafael.tictactoe.core.services.music

import android.app.Application
import android.app.Service
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.IBinder
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.ExoPlayer.REPEAT_MODE_ALL
import com.rafael.tictactoe.core.services.R
import org.koin.java.KoinJavaComponent.inject

actual class MusicService : Service() {

    private val context: Context by inject(Application::class.java)
    private val exoPlayer: ExoPlayer = ExoPlayer.Builder(context).build()
    private val backgroundMusicFile = R.raw.background_music
    private val backgroundMusic =
        MediaItem.fromUri(Uri.parse("android.resource://${context.packageName}/$backgroundMusicFile"))
            .also {
                println(context.packageName)
            }

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        startBackgroundMusic()
        return START_STICKY
    }

    private fun startBackgroundMusic() {
        exoPlayer.run {
            addMediaItem(backgroundMusic)
            prepare()
            repeatMode = REPEAT_MODE_ALL
            play()
        }
    }

    override fun onDestroy() {
        exoPlayer.run {
            stop()
            release()
        }
        super.onDestroy()
    }
}
