package com.rafael.tictactoe.feature.changelogs.data

import com.rafael.tictactoe.core.domain.util.getVersionName
import com.rafael.tictactoe.feature.changelogs.Res
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.invoke
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.MissingResourceException

internal class ChangelogGetter {
    companion object {
        @OptIn(ExperimentalResourceApi::class)
        suspend fun getCurrent(): Pair<String, String> {
            val version = getVersionName()
            val versionNumber = if (version.contains("-")) version.split("-")[0] else version
            val variant = if (version.contains("-")) version.split("-")[1] else ""
            val correctedVariant = if (variant != "") "" else variant
            var changelogContent: Pair<String, String> = Pair("", "")
            try {
                Res.readBytes("files/changelogs/$versionNumber$correctedVariant.txt").also {
                    changelogContent = Pair(versionNumber, it.decodeToString())
                }
            } catch (e: MissingResourceException) {
                e.printStackTrace()
            }

            return if (changelogContent.first.isNotEmpty() && changelogContent.second.isNotEmpty()) {
                changelogContent
            } else {
                Pair(
                    versionNumber,
                    "No changelogs provided for this version",
                )
            }
        }

        @OptIn(ExperimentalResourceApi::class)
        suspend fun getAll(): List<Pair<String, String>> {
            val changelogFiles = mutableListOf<Pair<String, String>>()

            try {
                (Dispatchers.IO) {
                    Res.readBytes("files/changelogs/fileIndex.txt")
                        .decodeToString()
                        .split("\n")
                        .map { it.trim() }
                        .forEach { changelogFileName ->
                            Res.readBytes("files/changelogs/$changelogFileName")
                                .decodeToString()
                                .also { changelogContent ->
                                    changelogFiles.add(changelogFileName.removeSuffix(".txt") to changelogContent)
                                }
                        }
                }
            } catch (e: MissingResourceException) {
                e.printStackTrace()
            }

            return if (changelogFiles.isNotEmpty()) {
                changelogFiles.reversed()
            } else {
                listOf(
                    Pair(
                        "unknown version",
                        "no changelogs founded :(",
                    ),
                )
            }
        }
    }
}
