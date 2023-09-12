package com.rafael.tictactoe.common.domain.util

import android.app.ActivityManager
import android.app.Service
import android.content.Context

fun RunningServiceVerifier(context: Context, serviceClass: Class<*>): Boolean {
	val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val runningProcesses = activityManager.runningAppProcesses

    for (runningProcess in runningProcesses) {
   	 if (runningProcess.processName == serviceClass.name) {
       	 return true
   	 }
        return false
    }
    return false
}