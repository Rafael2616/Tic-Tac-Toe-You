package com.rafael.tictactoe

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.lifecycle.*
import com.rafael.tictactoe.TicTacToeApp
import com.rafael.tictactoe.common.domain.util.AppForegroundNotifier

class AppLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
	
    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
        // Do nothing
    }

    override fun onActivityStarted(activity: Activity) {
        println("The app is in foreground.")
		AppForegroundNotifier(true)
    }

    override fun onActivityResumed(activity: Activity) {
        // Do nothing
    }

    override fun onActivityPaused(activity: Activity) {
        // Do nothing
    }

    override fun onActivityStopped(activity: Activity) {
        println("The app is in background.")
		AppForegroundNotifier(false)
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
        // Do nothing
    }

    override fun onActivityDestroyed(activity: Activity) {
        // Do nothing
    }
}