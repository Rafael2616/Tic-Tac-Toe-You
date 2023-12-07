package com.rafael.tictactoe.game.domain.model

import androidx.room.TypeConverter

class BooleanListConverter {
    @TypeConverter
    fun fromBooleanList(booleanList: List<Boolean>): String {
        return booleanList.joinToString(separator = ",") { it.toString() }
    }

    @TypeConverter
    fun toBooleanList(booleanListString: String): List<Boolean> {
        return booleanListString.split(",").map { it.toBoolean() }
    }
}
