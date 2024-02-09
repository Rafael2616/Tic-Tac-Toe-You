package com.rafael.tictactoe.game.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.Colors

data class Level(
    val level: CPUDifficultyLevel,
    val name: Int,
    val icon: ImageVector,
    val tint: Color,
) {
    companion object {
        fun getLevels(isDarkThemeOn: Boolean): List<Level> {
            return listOf(
                Level(
                    CPUDifficultyLevel.Easy,
                    R.string.level_easy,
                    Icons.Filled.SentimentSatisfied,
                    if (isDarkThemeOn) Color.Green else Color(0xFF299961),
                ),
                Level(
                    CPUDifficultyLevel.Medium,
                    R.string.level_medium,
                    Icons.Filled.SentimentNeutral,
                    if (isDarkThemeOn) Color.Yellow else Colors.get().burnt_yellow,
                ),
                Level(
                    CPUDifficultyLevel.Hard,
                    R.string.level_hard,
                    Icons.Filled.SentimentDissatisfied,
                    Color.Red,
                ),
            )
        }
    }
}
