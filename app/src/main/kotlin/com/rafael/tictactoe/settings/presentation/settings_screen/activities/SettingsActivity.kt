package com.rafael.tictactoe.settings.presentation.settings_screen.activities

import android.content.*
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.runtime.livedata.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import androidx.lifecycle.*
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.ui.theme.AppTheme
import com.rafael.tictactoe.game.presentation.main_screen.activities.MainActivity
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsTopBar
import com.rafael.tictactoe.settings.presentation.settings_screen.components.BackButton
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsCardsImpl


@AndroidEntryPoint
class SettingsActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)        
          setContent{ AppTheme(this) { settingsScreen()
      }
   }
}
override fun onBackPressed() {
    startActivity(Intent(this, MainActivity::class.java))
}

@Composable
fun settingsScreen() {
   
   val viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
   val isSystemInDarkTheme = isSystemInDarkTheme()
   val isDarkThemeOn by viewModel.isDarkThemeOn.collectAsState()
   val isFollowingSystemOn by viewModel.isFollowingSystemOn.collectAsState()
   val isDynamicColorsOn by viewModel.isDynamicColorsOn.collectAsState()
   val isBackgroundMusicOn by viewModel.isBackgroundMusicOn.collectAsState()
   val isSoundEffectsOn by viewModel.isSoundEffectsOn.collectAsState()
   val isAboutShown by viewModel.isAboutShown.collectAsState()


Surface(
    color = MaterialTheme.colorScheme.surface,
    modifier = Modifier.fillMaxSize()
) {

SettingsTopBar()
BackButton()
SettingsCardsImpl(viewModel, isFollowingSystemOn,isDarkThemeOn,isDynamicColorsOn,isSoundEffectsOn,isBackgroundMusicOn,isAboutShown)
}


@Composable
fun followSystemTheme(){

if (isSystemInDarkTheme){
viewModel.updateIsDarkThemeOnValue(true)
} else {
viewModel.updateIsDarkThemeOnValue(false)
   }
}
followSystemTheme()


} //SETTINGS SCREEN FUN ENDS HERE

}