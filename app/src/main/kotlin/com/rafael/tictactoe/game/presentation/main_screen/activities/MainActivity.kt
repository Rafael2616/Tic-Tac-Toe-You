package com.rafael.tictactoe.game.presentation.main_screen.activities

import android.content.*
import android.os.Bundle
//import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
import androidx.activity.viewModels
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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val settingsVM by viewModels<SettingsViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
			setContent{ AppTheme(this) { HomeScreen()
			} 
		}
	}

	@Composable
	fun HomeScreen() {
	
        val isSoundEffectsOn by settingsVM.isSoundEffectsOn.collectAsState()
	    val isAppInForeground by settingsVM.isAppInForeground.collectAsState()
		val isContentSetted by settingsVM.isContentSetted.collectAsState()
		val isDarkThemeOn by settingsVM.isDarkThemeOn.collectAsState()
        val isFollowingSystemOn by settingsVM.isFollowingSystemOn.collectAsState()
        val isDynamicColorsOn by settingsVM.isDynamicColorsOn.collectAsState()

		//Below the logic that decides in  which case each function must be called
        //and params needed for then works and update the UI if thats the case

		Surface(
   		 color = MaterialTheme.colorScheme.surface,
			modifier = Modifier.fillMaxSize()
  	  ) {
        	TopAppBar()
       	 OptionsButtons(isSoundEffectsOn)
		}
		
		if(!isContentSetted) {
			setContent{
				AppTheme(this, isDarkThemeOn, isFollowingSystemOn, isDynamicColorsOn) {
					HomeScreen()
				}
			}
		}
	}
}