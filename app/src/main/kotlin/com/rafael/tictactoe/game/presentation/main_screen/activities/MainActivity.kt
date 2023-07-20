package com.rafael.tictactoe.game.presentation.main_screen.activities

import android.content.*
import android.os.Bundle
//import android.widget.Toast
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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import com.rafael.tictactoe.settings.presentation.settings_screen.activities.SettingsActivity

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
			setContent{ AppTheme(this) { homeScreen()
			} 
		}
	}

	@Composable
	fun homeScreen() {
        val settingsVM = ViewModelProvider(this).get(SettingsViewModel::class.java)
        val isSoundEffectsOn by settingsVM.isSoundEffectsOn.collectAsState()

	Surface(
   	 color = MaterialTheme.colorScheme.surface,
		modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar()
        OptionsButtons(isSoundEffectsOn)
		}
	}
}