package com.rafael.tictactoe.core.platform

expect class Platform {
    companion object {
        val IS_IOS: Boolean
        val IS_ANDROID: Boolean
        val IS_WINDOWS: Boolean
    }
}
