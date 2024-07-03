package com.rafael.tictactoe.core.services.util

import android.app.ActivityManager
import android.content.Context

fun runningServiceVerifier(context: Context, serviceClass: Class<*>): Boolean {
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val runningProcesses = activityManager.runningAppProcesses

    runningProcesses.forEach { runningProcess ->
        if (runningProcess.processName == serviceClass.name) {
            return true
        }
        return false
    }
    return false
}
