package com.rafael.tictactoe.game.presentation.game_screen.components.buttons

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel

@Composable
fun ExpandButton() {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val viewModel = hiltViewModel<GameViewModel>()
    val isExpandButtonShown by viewModel.isExpandButtonShown.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp, start = 15.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Icon(
            imageVector = if (isExpandButtonShown) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = "Expand Button",
       	 modifier = Modifier
                .size(45.dp)
                .clip(CircleShape)
                .clickable(onClick = { 
                    viewModel.showExpandButton(!isExpandButtonShown)
            })
        )      
    }
}