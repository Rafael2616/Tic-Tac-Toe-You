package com.rafael.tictactoe.core.domain.util

import com.rafael.tictactoe.core.domain.BuildConfig

actual val VERSION_CODE = BuildConfig.VERSION_CODE

actual fun getVersionName(): String =
    BuildConfig.VERSION_NAME + if (!BuildConfig.DEBUG) "" else "-${BuildConfig.BUILD_TYPE}"
