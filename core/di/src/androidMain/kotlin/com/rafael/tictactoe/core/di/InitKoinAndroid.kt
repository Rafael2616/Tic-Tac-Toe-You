package com.rafael.tictactoe.core.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

fun initKoinAndroid(application: Application) {
    startKoin {
        androidLogger()
        androidContext(application)

        modules(modules)
    }
}
