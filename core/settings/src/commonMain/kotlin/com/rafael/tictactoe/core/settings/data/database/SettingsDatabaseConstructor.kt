package com.rafael.tictactoe.core.settings.data.database

import androidx.room.RoomDatabaseConstructor

// The Room compiler will generate the `actual` implementations :)
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object SettingsDatabaseConstructor : RoomDatabaseConstructor<SettingsDatabase>
