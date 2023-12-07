package com.rafael.tictactoe.common.domain.util

import com.rafael.tictactoe.BuildConfig

fun getVersionCode(): Int {
    return BuildConfig.VERSION_CODE
}

fun getVersionName(): String {
    return BuildConfig.VERSION_NAME
}
