package com.rafael.tictactoe.game.presentation.main_screen.activities

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.rafael.tictactoe.common.presentation.theme.AppTheme
import com.rafael.tictactoe.game.presentation.main_screen.components.OptionsButtons
import com.rafael.tictactoe.game.presentation.main_screen.components.MainAppBar
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val settingsVM by viewModels<SettingsViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			AppTheme() {
   			 MainScreen()
			} 
		}
	}

	@Composable
	fun MainScreen() {	
	    val isAppInForeground by settingsVM.isAppInForeground.collectAsState()

		//Below the logic that decides in  which case each function must be called
        //and params needed for then works and update the UI if thats the case

		Surface(
   		 color = MaterialTheme.colorScheme.surface,
			modifier = Modifier.fillMaxSize()
  	  ) {
        	MainAppBar()
       	 OptionsButtons()
		}
	}
}