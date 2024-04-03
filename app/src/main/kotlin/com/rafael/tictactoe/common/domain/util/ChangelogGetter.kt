package com.rafael.tictactoe.common.domain.util

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader

fun getChangelogFile(context: Context): String {
    val version = getVersionName()
    val versionNumber = if (version.contains("-")) version.split("-")[0] else version
    val variant = if (version.contains("-")) version.split("-")[1] else ""
    val correctedVariant = if (variant != "") "" else variant
    val changelogFile = try {
        val inputStream: InputStream = context.assets.open("changelogs/$versionNumber$correctedVariant.txt")
        val reader = BufferedReader(InputStreamReader(inputStream))
        reader.readLines()
    } catch (e: IOException) {
        null
    }

    val changelogContent: List<String>? = changelogFile ?: run {
        Log.e("ChangelogGetter", "File not found: $versionNumber$correctedVariant.txt")
        null
    }

    return changelogContent?.joinToString("\n") ?: "No changelogs provided for this version"
}

fun Context.getChangelogFiles(): List<Pair<String, String>> {
    var changelogFiles = mutableListOf<Pair<String, String>>()

    try {
        assets.list("changelogs/")
            ?.filter { it.endsWith(".txt") }
            ?.forEach { changelogFile ->
                assets.open("changelogs/" + changelogFile).use { inputStream ->
                    val changelog = inputStream.bufferedReader().readText()
                    changelogFiles.add(Pair(changelogFile.removeSuffix(".txt"), changelog))
                }
            }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return if (changelogFiles.isNotEmpty()) changelogFiles.reversed() else listOf(Pair("unknown version", "no changelogs founded :("))
}
