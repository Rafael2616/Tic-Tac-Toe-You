package com.rafael.tictactoe.core.domain.util

import platform.Foundation.NSBundle

actual val VERSION_CODE: Int =
    (NSBundle.mainBundle.infoDictionary["CFBundleVersion"] as String? ?: "0").toInt()

actual fun getVersionName(): String =
    NSBundle.mainBundle.infoDictionary["CFBundleShortVersionString"] as String? ?: ""
