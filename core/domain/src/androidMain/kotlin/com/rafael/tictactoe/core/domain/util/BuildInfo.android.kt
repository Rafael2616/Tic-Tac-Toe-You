package com.rafael.tictactoe.core.domain.util

import com.rafael.tictactoe.core.domain.BuildConfig

actual object BuildInfo {
    actual val APP_ID: String = "com.rafael.tictactoe" + if (!BuildConfig.DEBUG) "" else ".${BuildConfig.BUILD_TYPE}"
    actual val VERSION_CODE: Int = BuildConfig.VERSION_CODE
    actual val VERSION_NAME: String = BuildConfig.VERSION_NAME + if (!BuildConfig.DEBUG) "" else "-${BuildConfig.BUILD_TYPE}"
}
