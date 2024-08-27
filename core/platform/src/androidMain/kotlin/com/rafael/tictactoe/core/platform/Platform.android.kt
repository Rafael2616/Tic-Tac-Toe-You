package com.rafael.tictactoe.core.platform

actual class Platform {
    actual companion object {
        actual val IS_IOS: Boolean = false
        actual val IS_ANDROID: Boolean = true
        actual val IS_WINDOWS: Boolean = false
    }
}
