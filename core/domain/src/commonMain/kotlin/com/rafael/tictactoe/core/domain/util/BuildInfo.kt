package com.rafael.tictactoe.core.domain.util

expect object BuildInfo {
    val APP_ID: String
    val VERSION_CODE: Int
    val VERSION_NAME: String
}
