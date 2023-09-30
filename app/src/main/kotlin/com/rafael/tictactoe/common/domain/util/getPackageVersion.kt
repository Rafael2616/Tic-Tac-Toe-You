package com.rafael.tictactoe.common.domain.util

import android.content.Context

fun getPackageVersion(context: Context): String {
    val packageName = "com.rafael.tictactoe"
    val packageManager = context.packageManager
    val packageInfo = packageManager.getPackageInfo(packageName, 0)
    val versionName = packageInfo.versionName

    return (versionName)
}