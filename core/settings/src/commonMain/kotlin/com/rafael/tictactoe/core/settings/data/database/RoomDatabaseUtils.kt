package com.rafael.tictactoe.core.settings.data.database

import androidx.room3.RoomDatabase
import kotlin.reflect.KClass

expect inline fun <reified T : RoomDatabase> getRoomDatabase(
    klass: KClass<T>,
    databaseName: String,
): RoomDatabase.Builder<T>
