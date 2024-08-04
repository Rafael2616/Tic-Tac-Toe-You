package com.rafael.tictactoe

import android.app.Application
import com.rafael.tictactoe.core.navigation.impl.modules.modules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class TicTacToeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@TicTacToeApp)

            modules(modules)
        }
    }
}
