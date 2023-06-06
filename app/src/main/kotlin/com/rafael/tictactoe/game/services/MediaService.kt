package com.rafael.tictactoe.game.services

import android.os.IBinder
import android.app.Service
import android.app.ActivityManager
import android.annotation.SuppressLint
import android.media.MediaPlayer
import android.content.*
import com.rafael.tictactoe.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MediaService : Service() {
private var mediaPlayer: MediaPlayer? = null

override fun onBind(intent: Intent): IBinder? { 
return null
}

override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
       val sharedPreferences = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
       var isMusicOn = sharedPreferences.getBoolean("isBackgroundMusicOn", true)
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music)
        mediaPlayer?.isLooping = true
      
if(isMusicOn){
    mediaPlayer?.start()
    } else {
       mediaPlayer?.stop()
       }
    return START_STICKY
}
        
override fun onDestroy() {
    super.onDestroy()
    mediaPlayer?.stop()
    mediaPlayer?.release()       
    }

}