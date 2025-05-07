package com.rafael.tictactoe.core.navigation.impl.types

import androidx.navigation.NavType
import androidx.savedstate.SavedState
import androidx.savedstate.read
import androidx.savedstate.write
import com.rafael.tictactoe.core.navigation.GameMode
import kotlinx.serialization.json.Json

val gameModeNavType = object : NavType<GameMode>(
    isNullableAllowed = false,
) {
    val json = Json { ignoreUnknownKeys = true }
    override fun get(bundle: SavedState, key: String): GameMode? {
        val jsonString = bundle.read { getString(key) }
        return json.decodeFromString(jsonString)
    }

    override fun put(bundle: SavedState, key: String, value: GameMode) {
        bundle.write { putString(key, json.encodeToString(value)) }
    }

    override fun parseValue(value: String): GameMode = json.decodeFromString(value)

    override fun serializeAsValue(value: GameMode): String = json.encodeToString(value)
}
