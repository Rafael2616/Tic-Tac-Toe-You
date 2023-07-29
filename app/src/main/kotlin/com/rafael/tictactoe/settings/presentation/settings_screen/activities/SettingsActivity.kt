package com.rafael.tictactoe.settings.presentation.settings_screen.activities

import android.content.*
import android.os.Bundle
import android.widget.Toast
import android.app.ActivityManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.lifecycle.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.ui.theme.AppTheme
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.game.presentation.main_screen.activities.MainActivity
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsTopBar
import com.rafael.tictactoe.settings.presentation.settings_screen.components.BackButton
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsCardsImpl
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.*

@AndroidEntryPoint
class SettingsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(this) {
                settingsScreen()
            }
        }
    }

    override fun onBackPressed() {
		if(isTaskRoot) {
        	startActivity(Intent(this, MainActivity::class.java))
		} else {
			finish()
		}
	}
	
	@Inject
	lateinit var repo: SettingsRepository
	
    @Composable
    fun settingsScreen() {
	
        val viewModel = ViewModelProvider(this).get(SettingsViewModel::class.java)
        val isDarkThemeOn by viewModel.isDarkThemeOn.collectAsState()
        val isFollowingSystemOn by viewModel.isFollowingSystemOn.collectAsState()
        val isDynamicColorsOn by viewModel.isDynamicColorsOn.collectAsState()
        val isContentSetted by viewModel.isContentSetted.collectAsState()

		//Below the logic that decides in  which case each function must be called
        //and params needed for then works and update the UI if thats the case

        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxSize()
        ) {
            BackButton(this, viewModel, this, isTaskRoot)
            SettingsTopBar()
            SettingsCardsImpl(this, viewModel)
        }
		
		if (!isContentSetted) {
			setContent {
				AppTheme(this, isDarkThemeOn, isFollowingSystemOn, isDynamicColorsOn) {
					settingsScreen()
				}
			}
		}
    }
}