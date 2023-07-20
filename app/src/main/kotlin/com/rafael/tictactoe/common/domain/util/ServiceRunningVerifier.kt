package com.rafael.tictactoe.commom.domain.util

import android.app.ActivityManager
import android.content.Context

fun ServiceRunningVerifier(context: Context, serviceClass: Class<*>): Boolean {
	val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val runningServices = activityManager.getRunningServices(Int.MAX_VALUE)

    for (serviceInfo in runningServices) {
   	 val className = serviceInfo.service.className
   	 if (className == serviceClass.name) {
       	 return true
   	 }
    }
    return false
}