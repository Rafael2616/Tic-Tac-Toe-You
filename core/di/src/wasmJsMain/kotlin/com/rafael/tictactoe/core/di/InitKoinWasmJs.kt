package com.rafael.tictactoe.core.di

import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.core.logger.PrintLogger

fun initKoinWasmJs() {
    startKoin {
        logger(PrintLogger(Level.DEBUG))
        modules(modules)
    }
}
