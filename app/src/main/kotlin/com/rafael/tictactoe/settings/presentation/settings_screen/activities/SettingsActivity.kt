package com.rafael.tictactoe.settings.presentation.settings_screen.activities

import android.content.*
import android.os.Bundle
import android.widget.Toast
import android.app.ActivityManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
import androidx.activity.viewModels
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
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsCardsImpl
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsTopBar
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class SettingsActivity : ComponentActivity() {

    val settingsVM by viewModels<SettingsViewModel>()

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
		settingsVM.updateIsAboutShown(false)
	}

	@Inject
	lateinit var repo: SettingsRepository
	
    @Composable
    fun settingsScreen() {
	
        val isDarkThemeOn by settingsVM.isDarkThemeOn.collectAsState()
        val isFollowingSystemOn by settingsVM.isFollowingSystemOn.collectAsState()
        val isDynamicColorsOn by settingsVM.isDynamicColorsOn.collectAsState()
        val isContentSetted by settingsVM.isContentSetted.collectAsState()

        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxSize()
        ) {
            SettingsTopBar(this, settingsVM, this)
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