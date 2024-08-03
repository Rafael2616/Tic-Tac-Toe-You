package com.rafael.tictactoe.core.services.util

import android.app.ActivityManager
import android.content.Context

fun runningServiceVerifier(context: Context, serviceClass: Class<*>): Boolean {
    val activityManager =
        context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    @Suppress("DEPRECATION")
    for (service in activityManager.getRunningServices(Integer.MAX_VALUE)) {
        if (serviceClass.name == service.service.className) {
            return true
        }
    }
    return false
}
