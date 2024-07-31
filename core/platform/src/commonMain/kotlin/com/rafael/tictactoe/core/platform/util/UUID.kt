package com.rafael.tictactoe.core.platform.util

import kotlinx.datetime.Clock

// Custom implementation of java.util.UUID.randomUUID() for use in Kotlin Multiplatform.
interface UUID {
    companion object {
        fun randomUUID(): Long {
            val instant = Clock.System.now()
            return instant.toEpochMilliseconds()
        }
    }
}
