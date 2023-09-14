package com.rafael.tictactoe.game.domain.model

import androidx.room.TypeConverter

<<<<<<< HEAD
=======

>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
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
