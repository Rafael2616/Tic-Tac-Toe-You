package com.rafael.tictactoe.feature.changelogs.data

import android.app.Application
import com.rafael.tictactoe.core.domain.util.getVersionName
import java.io.IOException
import org.koin.java.KoinJavaComponent.inject

actual fun getCurrentChangelogFile(): Pair<String, String> {
    val version = getVersionName()
    val versionNumber = if (version.contains("-")) version.split("-")[0] else version
    val variant = if (version.contains("-")) version.split("-")[1] else ""
    val correctedVariant = if (variant != "") "" else variant
    var changelogContent: Pair<String, String> = Pair("", "")
    val context: Application by inject(Application::class.java)

    try {
        context.assets.open("changelogs/$versionNumber$correctedVariant.txt").use { inputStream ->
            inputStream.bufferedReader().readText().also {
                changelogContent = Pair(versionNumber, it)
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return if (changelogContent.first.isNotEmpty() && changelogContent.second.isNotEmpty()) changelogContent else Pair(versionNumber, "No changelogs provided for this version")
}

actual fun getChangelogFiles(): List<Pair<String, String>> {
    val changelogFiles = mutableListOf<Pair<String, String>>()
    val context: Application by inject(Application::class.java)

    try {
        context.assets.list("changelogs/")
            ?.filter { it.endsWith(".txt") }
            ?.forEach { changelogFile ->
                context.assets.open("changelogs/$changelogFile").use { inputStream ->
                    inputStream.bufferedReader().readText().also {
                        changelogFiles.add(Pair(changelogFile.removeSuffix(".txt"), it))
                    }
                }
            }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return if (changelogFiles.isNotEmpty()) changelogFiles.reversed() else listOf(Pair("unknown version", "no changelogs founded :("))
}
