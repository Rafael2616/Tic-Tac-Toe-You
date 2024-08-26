package com.rafael.tictactoe.core.di

import org.koin.core.context.startKoin

fun initKoinJvm() {
    startKoin {
        modules(modules)
    }
}
