package com.rafael.tictactoe.game.presentation.main_screen

import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.common.presentation.theme.AppTheme
import com.rafael.tictactoe.game.presentation.main_screen.components.OptionsButtons
import com.rafael.tictactoe.game.presentation.main_screen.components.MainAppBar
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun MainScreen(navController: NavController) {
    val settingsVM = viewModel<SettingsViewModel>()	
    val isAppInForeground by settingsVM.isAppInForeground.collectAsState()

	//Below the logic that decides in  which case each function must be called
    //and params needed for then works and update the UI if thats the case

	Surface(
   	 color = MaterialTheme.colorScheme.surface,
		modifier = Modifier.fillMaxSize()
    ) {
    	MainAppBar()
   	 OptionsButtons(navController)
	}
}