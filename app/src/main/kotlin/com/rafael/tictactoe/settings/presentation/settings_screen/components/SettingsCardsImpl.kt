package com.rafael.tictactoe.settings.presentation.settings_screen.components

import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsCards
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun SettingsCardsImpl(paddingValues: PaddingValues) {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val isFollowingSystemOn by settingsVM.isFollowingSystemOn.collectAsState()
    val isDynamicColorsSupported = VERSION.SDK_INT >= VERSION_CODES.S
    var cards by rememberSaveable { mutableStateOf(List<Boolean>(6) { false }) }

   LazyColumn(
        modifier = Modifier.
            fillMaxSize()
            .padding(paddingValues),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(cards.size) { index ->
            if (((index == 1) && (!isFollowingSystemOn)) ||
                ((index == 2) && (isDynamicColorsSupported)) ||
                ((index != 1) && (index != 2))) {
                SettingsCards(index)
            }
        }
    }
}