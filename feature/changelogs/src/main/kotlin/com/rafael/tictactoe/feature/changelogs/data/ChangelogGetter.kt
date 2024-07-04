package com.rafael.tictactoe.feature.changelogs.data

import android.content.Context
import com.rafael.tictactoe.core.domain.util.getVersionName
import java.io.IOException

internal fun Context.getCurrentChangelogFile(): Pair<String, String> {
    val version = getVersionName()
    val versionNumber = if (version.contains("-")) version.split("-")[0] else version
    val variant = if (version.contains("-")) version.split("-")[1] else ""
    val correctedVariant = if (variant != "") "" else variant
    var changelogContent: Pair<String, String> = Pair("", "")

    try {
        assets.open("changelogs/$versionNumber$correctedVariant.txt").use { inputStream ->
            val changelog = inputStream.bufferedReader().readText().also {
                changelogContent = Pair(versionNumber, it)
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return if (changelogContent.first.isNotEmpty() && changelogContent.second.isNotEmpty()) changelogContent else Pair(versionNumber, "No changelogs provided for this version")
}

internal fun Context.getChangelogFiles(): List<Pair<String, String>> {
    val changelogFiles = mutableListOf<Pair<String, String>>()

    try {
        assets.list("changelogs/")
            ?.filter { it.endsWith(".txt") }
            ?.forEach { changelogFile ->
                assets.open("changelogs/$changelogFile").use { inputStream ->
                    val changelog = inputStream.bufferedReader().readText()
                    changelogFiles.add(Pair(changelogFile.removeSuffix(".txt"), changelog))
                }
            }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return if (changelogFiles.isNotEmpty()) changelogFiles.reversed() else listOf(Pair("unknown version", "no changelogs founded :("))
}
