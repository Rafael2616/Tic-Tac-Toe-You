package com.rafael.tictactoe.core.platform

actual class Platform {
    actual companion object {
        actual val IS_IOS: Boolean = true
        actual val IS_ANDROID: Boolean = false
        actual val IS_WINDOWS: Boolean = false
    }
}
