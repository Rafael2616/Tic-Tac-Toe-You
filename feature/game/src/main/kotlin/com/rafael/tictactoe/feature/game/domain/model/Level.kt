package com.rafael.tictactoe.feature.game.domain.model

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.core.domain.model.Colors

data class Level(
    val level: CPUDifficultyLevel,
    @StringRes val name: Int,
    val icon: ImageVector,
    val tint: Color,
    val index: Int,
) {
    companion object {
        fun getLevels(isDarkThemeOn: Boolean): List<Level> {
            return listOf(
                Level(
                    level = CPUDifficultyLevel.Easy,
                    name = R.string.level_easy,
                    icon = Icons.Filled.SentimentSatisfied,
                    tint = if (isDarkThemeOn) Color.Green else Color(0xFF299961),
                    index = 0,
                ),
                Level(
                    level = CPUDifficultyLevel.Medium,
                    name = R.string.level_medium,
                    icon = Icons.Filled.SentimentNeutral,
                    tint = if (isDarkThemeOn) Color.Yellow else Colors.get().burnt_yellow,
                    index = 1,
                ),
                Level(
                    level = CPUDifficultyLevel.Hard,
                    name = R.string.level_hard,
                    icon = Icons.Filled.SentimentDissatisfied,
                    tint = Color.Red,
                    index = 2,
                ),
            )
        }
    }
}
