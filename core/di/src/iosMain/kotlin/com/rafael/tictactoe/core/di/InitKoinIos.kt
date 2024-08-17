package com.rafael.tictactoe.core.di

import org.koin.core.context.startKoin

fun initKoinIos() {
    startKoin {
        modules(modules)
    }
}
