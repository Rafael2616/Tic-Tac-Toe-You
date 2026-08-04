package com.rafael.tictactoe.feature.changelogs.domain

import androidx.compose.runtime.Stable

@Stable
data class Changelog(
    val version: String = "",
    val changes: String = "",
)
