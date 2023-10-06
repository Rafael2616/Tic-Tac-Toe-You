package com.rafael.tictactoe.settings.domain.util

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun SettingsIconHandler(index: Int, groupType: GroupType): ImageVector {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val isAboutShown by viewModel.isAboutShown.collectAsState()

    @Composable
    fun CustomizationIconHandler(index: Int): ImageVector {
        return when (index) {
            0 -> Icons.AutoMirrored.Filled.ArrowForward
            1 -> Icons.AutoMirrored.Filled.ArrowForward
            2 -> Icons.AutoMirrored.Filled.ArrowForward
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