package com.rafael.tictactoe.feature.game.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SentimentDissatisfied
import androidx.compose.material.icons.filled.SentimentNeutral
import androidx.compose.material.icons.filled.SentimentSatisfied
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.rafael.tictactoe.core.domain.util.Colors
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.level_easy
import com.rafael.tictactoe.core.resources.level_hard
import com.rafael.tictactoe.core.resources.level_medium
import org.jetbrains.compose.resources.StringResource

data class Level(
    val level: CPUDifficultyLevel,
    val name: StringResource,
    val icon: ImageVector,
    val tint: Color,
    val index: Int,
) {
    companion object {
        fun getLevels(isDarkThemeOn: Boolean): List<Level> = listOf(
            Level(
                level = CPUDifficultyLevel.Easy,
                name = Res.string.level_easy,
                icon = Icons.Filled.SentimentSatisfied,
                tint = if (isDarkThemeOn) Color.Green else Color(0xFF299961),
                index = 0,
            ),
            Level(
                level = CPUDifficultyLevel.Medium,
                name = Res.string.level_medium,
                icon = Icons.Filled.SentimentNeutral,
                tint = if (isDarkThemeOn) Color.Yellow else Colors.get().burntYellow,
                index = 1,
            ),
            Level(
                level = CPUDifficultyLevel.Hard,
                name = Res.string.level_hard,
                icon = Icons.Filled.SentimentDissatisfied,
                tint = Color.Red,
                index = 2,
            ),
        )
    }
}
