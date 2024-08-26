package com.rafael.tictactoe.core.navigation.impl.types

import androidx.core.bundle.Bundle
import androidx.navigation.NavType
import com.rafael.tictactoe.core.game.domain.model.GameMode
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

val gameModeNavType = object : NavType<GameMode>(
    isNullableAllowed = false,
) {
    val json = Json { ignoreUnknownKeys = true }
    override fun get(bundle: Bundle, key: String): GameMode? {
        return json.decodeFromString(bundle.getString(key) ?: return null)
    }

    override fun put(bundle: Bundle, key: String, value: GameMode) {
        bundle.putString(key, json.encodeToString(value))
    }

    override fun parseValue(value: String): GameMode = json.decodeFromString(value)

    override fun serializeAsValue(value: GameMode): String = json.encodeToString(value)
}
