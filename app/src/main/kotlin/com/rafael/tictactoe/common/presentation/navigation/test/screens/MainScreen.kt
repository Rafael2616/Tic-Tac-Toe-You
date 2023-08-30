package com.rafael.tictactoe.game.presentation.main_screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.common.presentation.theme.AppTheme
import com.rafael.tictactoe.game.presentation.main_screen.components.test.OptionsButtons
import com.rafael.tictactoe.game.presentation.main_screen.components.test.MainAppBar
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun MainScreen(navController: NavController, settingsVM: SettingsViewModel) {
//    val settingsVM = viewModel<SettingsViewModel>()	
    val isAppInForeground by settingsVM.isAppInForeground.collectAsState()

	//Below the logic that decides in  which case each function must be called
    //and params needed for then works and update the UI if needed

    MainAppBar()
    OptionsButtons(navController, settingsVM)
}