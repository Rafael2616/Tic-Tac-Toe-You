package com.rafael.tictactoe

import android.app.Application
import com.rafael.tictactoe.core.di.initKoinAndroid

class TicTacToeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoinAndroid(this@TicTacToeApp)
    }
}
