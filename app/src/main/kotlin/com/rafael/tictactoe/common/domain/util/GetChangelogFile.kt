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
