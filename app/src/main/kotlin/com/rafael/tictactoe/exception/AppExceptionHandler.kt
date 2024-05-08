package com.rafael.tictactoe.exception

import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.system.exitProcess

class AppExceptionHandler private constructor(
    private val defaultHandler: Thread.UncaughtExceptionHandler,
) : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(p0: Thread, p1: Throwable) {
        val fileName = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "crash.log")
        val crashLogsFile = FileOutputStream(fileName)
        val sw = StringWriter()
        p1.printStackTrace(PrintWriter(sw))
        crashLogsFile.use { it.write(sw.toString().toByteArray()) }
        kotlin.runCatching {
            Log.e(this.toString(), p1.stackTraceToString())
            exitProcess(0)
        }.getOrElse {
            defaultHandler.uncaughtException(p0, p1)
        }
        crashLogsFile.close()
    }

    companion object {
        fun init() = Thread.setDefaultUncaughtExceptionHandler(
            AppExceptionHandler(
                Thread.getDefaultUncaughtExceptionHandler() as Thread.UncaughtExceptionHandler,
            ),
        )
    }
}
