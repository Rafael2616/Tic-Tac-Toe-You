package com.rafael.tictactoe.core.settings.data.database

import android.app.Application
import androidx.room3.Room
import androidx.room3.RoomDatabase
import kotlin.reflect.KClass
import org.koin.java.KoinJavaComponent.inject

actual inline fun <reified T : RoomDatabase> getRoomDatabase(
    klass: KClass<T>,
    databaseName: String,
): RoomDatabase.Builder<T> {
    val application by inject<Application>(Application::class.java)
    return Room.databaseBuilder(
        context = application,
        klass = klass.java,
        name = databaseName,
    )
}
