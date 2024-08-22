package com.rafael.tictactoe.feature.changelogs.data

import com.rafael.tictactoe.core.domain.util.getVersionName
import com.rafael.tictactoe.feature.changelogs.Res
import com.rafael.tictactoe.feature.changelogs.domain.Changelog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.invoke
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.MissingResourceException

internal class ChangelogGetter {
    companion object {
        @OptIn(ExperimentalResourceApi::class)
        suspend fun getCurrent(): Changelog {
            val version = getVersionName()
            val versionNumber = if (version.contains("-")) version.split("-")[0] else version
            val variant = if (version.contains("-")) version.split("-")[1] else ""
            val correctedVariant = if (variant != "") "" else variant
            var changelogContent = Changelog()
            try {
                (Dispatchers.IO) {
                    Res.readBytes("files/changelogs/$versionNumber$correctedVariant.txt").also {
                        if (it.isNotEmpty()) {
                            changelogContent = Changelog(versionNumber, it.decodeToString())
                        }
                    }
                }
            } catch (e: MissingResourceException) {
                e.printStackTrace()
            }

            return if (changelogContent.version.isNotEmpty() && changelogContent.changes.isNotEmpty()) {
                changelogContent
            } else {
                Changelog(
                    versionNumber,
                    "No changelogs provided for this version",
                )
            }
        }

        @OptIn(ExperimentalResourceApi::class)
        suspend fun getAll(): List<Changelog> {
            val changelogFiles = mutableListOf<Changelog>()

            try {
                (Dispatchers.IO) {
                    Res.readBytes("files/changelogs/fileIndex.txt")
                        .decodeToString()
                        .split("\n")
                        .filter { it.isNotEmpty() }
                        .map { it.trim() }
                        .forEach { changelogFileName ->
                            Res.readBytes("files/changelogs/$changelogFileName")
                                .decodeToString()
                                .also { changelogContent ->
                                    changelogFiles.add(
                                        Changelog(
                                            version = changelogFileName.removeSuffix(".txt"),
                                            changes = changelogContent,
                                        ),
                                    )
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
                    Changelog(
                        "unknown version",
                        "no changelogs founded :(",
                    ),
                )
            }
        }
    }
}
