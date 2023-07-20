package com.rafael.tictactoe.common.services

import android.os.IBinder
import android.app.Service
import android.media.MediaPlayer
import android.content.*
import com.rafael.tictactoe.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MusicService : Service() {

    private var backgroundMusic: MediaPlayer? = null

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
		startBackgroundMusic()
    	return START_STICKY
    }
	
	fun startBackgroundMusic(){
	    backgroundMusic = MediaPlayer.create(this, R.raw.background_music)
   	 backgroundMusic?.isLooping = true
		backgroundMusic?.start()
	}
	
    override fun onDestroy() {
        super.onDestroy()
        backgroundMusic?.stop()
        backgroundMusic?.release()
    }
}