package com.rafael.tictactoe.game.presentation.main_screen.activities

import android.content.*
import android.os.Bundle
//import android.widget.Toast
import androidx.activity.viewModels
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
//import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.lifecycle.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import com.rafael.tictactoe.common.presentation.ui.theme.AppTheme
import com.rafael.tictactoe.game.presentation.main_screen.components.OptionsButtons
import com.rafael.tictactoe.game.presentation.main_screen.components.TopAppBar
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.game.domain.util.BackgroundMusic
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
			setContent{ AppTheme(this) { HomeScreen()
			} 
		}
	}

	@Composable
	fun HomeScreen() {
	
        val settingsVM = ViewModelProvider(this).get(SettingsViewModel::class.java)
        val isSoundEffectsOn by settingsVM.isSoundEffectsOn.collectAsState()
	    val isBackgroundMusicOn by settingsVM.isBackgroundMusicOn.collectAsState()
	    val isAppInForeground by settingsVM.isAppInForeground.collectAsState()
		val isContentSetted by settingsVM.isContentSetted.collectAsState()
		val isDarkThemeOn by settingsVM.isDarkThemeOn.collectAsState()
	
		if(!isContentSetted) {
			setContent{
				AppTheme(this, isDarkThemeOn) {
					HomeScreen()
				}
			}
		}	
	Surface(
   	 color = MaterialTheme.colorScheme.surface,
		modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar()
        OptionsButtons(isSoundEffectsOn)
		BackgroundMusic(this, isBackgroundMusicOn)
		}
	}
}