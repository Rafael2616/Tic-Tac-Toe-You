package com.rafael.tictactoe.exception

import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.PrintWriter
import java.io.StringWriter
import kotlin.system.exitProcess

internal class AppExceptionHandler private constructor(
    private val defaultHandler: Thread.UncaughtExceptionHandler,
) : Thread.UncaughtExceptionHandler {

    override fun uncaughtException(p0: Thread, p1: Throwable) {
        try {
            val crashFile = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "crash.log",
            )
            val crashLogsFile = FileOutputStream(crashFile)
            val sw = StringWriter()

            p1.printStackTrace(PrintWriter(sw))
            crashLogsFile.use { it.write(sw.toString().toByteArray()) }
            runCatching {
                Log.e(this.toString(), p1.stackTraceToString())
                exitProcess(0)
            }.getOrElse {
                defaultHandler.uncaughtException(p0, p1)
            }
            crashLogsFile.close()
        } catch (e: IOException) {
            defaultHandler.uncaughtException(p0, p1)
        }
    }

    companion object {
        fun init() = Thread.setDefaultUncaughtExceptionHandler(
            AppExceptionHandler(
                Thread.getDefaultUncaughtExceptionHandler() as Thread.UncaughtExceptionHandler,
            ),
        )
    }
}
