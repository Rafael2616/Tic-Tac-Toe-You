package com.rafael.tictactoe

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TicTacToeApp : Application() {

    override fun onCreate() {
        super.onCreate()

        // Register ActivityLifecycleCallbacks to monitore all activities lifecycle
        registerActivityLifecycleCallbacks(AppLifecycleCallbacks())
    }
}