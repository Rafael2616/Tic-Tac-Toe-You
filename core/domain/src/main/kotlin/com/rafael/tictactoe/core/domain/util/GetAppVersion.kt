package com.rafael.tictactoe.core.domain.util

import com.rafael.tictactoe.core.domain.BuildConfig

fun getVersionCode(): Int {
    return BuildConfig.VERSION_CODE
}

fun getVersionName(): String {
    return BuildConfig.VERSION_NAME
}
