package com.rafael.tictactoe.core.domain.util

expect object BuildInfo {
    val APP_ID: String
    val VERSION_CODE: Int
    val VERSION_NAME: String
}

const val COMMON_APPLICATION_ID = "com.rafael.tictactoe"
const val COMMON_VERSION_CODE = 169
const val COMMON_VERSION_NAME = "1.7.7"
