package com.rafael.tictactoe.core.domain.util

import platform.Foundation.NSBundle

actual object BuildInfo {
    actual val APP_ID: String = COMMON_APPLICATION_ID
    actual val VERSION_CODE: Int = (NSBundle.mainBundle.infoDictionary["CFBundleVersion"] as String? ?: "0").toInt()
    actual val VERSION_NAME: String = NSBundle.mainBundle.infoDictionary["CFBundleShortVersionString"] as String? ?: ""
}
