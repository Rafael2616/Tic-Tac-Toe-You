package com.rafael.tictactoe.common.domain.util

import com.rafael.tictactoe.BuildConfig

fun getVersionName(): String {
    return BuildConfig.VERSION_NAME
}
fun getVersionCode(): Int {
    return BuildConfig.VERSION_CODE
}