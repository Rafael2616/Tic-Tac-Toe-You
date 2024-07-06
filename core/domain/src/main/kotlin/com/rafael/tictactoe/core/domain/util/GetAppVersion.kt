package com.rafael.tictactoe.core.domain.util

import com.rafael.tictactoe.core.domain.BuildConfig

const val VERSION_CODE = BuildConfig.VERSION_CODE

fun getVersionName(): String {
    return BuildConfig.VERSION_NAME + if (BuildConfig.BUILD_TYPE == "release") "" else "-${BuildConfig.BUILD_TYPE}"
}
