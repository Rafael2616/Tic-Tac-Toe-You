package com.rafael.tictactoe.common.services

import android.os.IBinder
import android.app.Service
//import android.app.ActivityManager
import android.media.MediaPlayer
import android.content.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.*


@AndroidEntryPoint
class MusicService : Service() {

    @Inject
    lateinit var settingsRepo: SettingsRepository

    private lateinit var backgroundMusic: MediaPlayer

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        CoroutineScope(Dispatchers.Main).launch {
            val settings = settingsRepo.getAllSettingsValues()
   	     val isBackgroundMusicOn = withContext(Dispatchers.IO) {
            settings.isBackgroundMusicOn
     	   }
     	   backgroundMusic = MediaPlayer.create(this@MusicService, R.raw.background_music)
       	 backgroundMusic.isLooping = true

    	    if (isBackgroundMusicOn) {
           	 backgroundMusic.start()
       	 } else {
          	  backgroundMusic.pause()
        	}
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        backgroundMusic.stop()
        backgroundMusic.release()
    }
}