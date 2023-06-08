package com.rafael.tictactoe.settings.presentation.settings_screen.components

import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsCards
import android.os.Build.*
import android.content.*
import androidx.activity.compose.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository


@Composable
fun SettingsCardsImpl(
viewModel: SettingsViewModel,
isFollowingSystemOn: Boolean,
isDarkThemeOn: Boolean,
isDynamicColorsOn: Boolean,
isSoundEffectsOn: Boolean,
isBackgroundMusicOn: Boolean,
isAboutShown: Boolean,
context: Context,
settingsRepo: SettingsRepository
) {

val isDynamicColorsSupported = VERSION.SDK_INT >= VERSION_CODES.S 
var cards by rememberSaveable { mutableStateOf(List<Boolean>(6) { true }) }

LazyColumn(
    modifier = Modifier.fillMaxSize().padding(top = 195.dp),
    verticalArrangement = Arrangement.Top,
    horizontalAlignment = Alignment.CenterHorizontally
) {
    items(cards.size) { index ->
       if (((index == 1) && (!isFollowingSystemOn)) ||
            ((index == 2) && (isDynamicColorsSupported)) ||
            ((index != 1) && (index != 2))) {
            SettingsCards(index,viewModel,isFollowingSystemOn,isDarkThemeOn,isDynamicColorsOn,isSoundEffectsOn,isBackgroundMusicOn,isAboutShown, context, settingsRepo)
        }
      }
   }
}