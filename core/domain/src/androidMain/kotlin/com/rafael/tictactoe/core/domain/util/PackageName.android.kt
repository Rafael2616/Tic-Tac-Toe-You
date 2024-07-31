package com.rafael.tictactoe.core.domain.util

import com.rafael.tictactoe.core.domain.BuildConfig

actual val PACKAGE_NAME: String
    get() = if (BuildConfig.DEBUG) "com.rafael.tictactoe.debug" else "com.rafael.tictactoe"
