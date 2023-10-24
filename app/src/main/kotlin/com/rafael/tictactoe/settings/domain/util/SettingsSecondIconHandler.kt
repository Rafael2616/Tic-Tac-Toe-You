package com.rafael.tictactoe.settings.domain.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.rafael.tictactoe.settings.domain.enums.GroupType
import com.rafael.tictactoe.settings.domain.model.SettingsState

@Composable
fun SettingsSecondIconHandler(
    index: Int,
    groupType: GroupType,
    state: SettingsState
): ImageVector {
    val isAboutShown = state.isAboutShown

    @Composable
    fun CustomizationIconHandler(index: Int): ImageVector {
        return when (index) {
            0 -> Icons.Filled.ArrowForward
            1 -> Icons.Filled.ArrowForward
            2 -> Icons.Filled.ArrowForward
            else -> Icons.Filled.Close
        }
    }

    @Composable
    fun OtherIconHandler(index: Int): ImageVector {
        return when (index) {
            0 -> if (isAboutShown) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore
            else -> Icons.Filled.Close
        }
    }

    return when (groupType) {
        GroupType.CUSTOMIZATION -> CustomizationIconHandler(index)
        GroupType.OTHER -> OtherIconHandler(index)
        else -> Icons.Filled.Close
    }
}