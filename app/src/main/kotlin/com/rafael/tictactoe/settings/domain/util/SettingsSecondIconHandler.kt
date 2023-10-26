package com.rafael.tictactoe.settings.domain.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
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

    @Composable
    fun CustomizationIconHandler(): ImageVector {
        return Icons.AutoMirrored.Filled.ArrowForward
    }

    @Composable
    fun OtherIconHandler(index: Int): ImageVector {
        return when (index) {
            0 -> if (state.isAboutShown) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore
            else -> Icons.Filled.Close
        }
    }

    return when (groupType) {
        GroupType.CUSTOMIZATION -> CustomizationIconHandler()
        GroupType.OTHER -> OtherIconHandler(index)
        else -> Icons.Filled.Close
    }
}