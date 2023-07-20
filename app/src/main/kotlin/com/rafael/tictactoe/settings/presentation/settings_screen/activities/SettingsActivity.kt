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
    lateinit var settingsRepo: SettingsRepository

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
        val isContentSetted by viewModel.isContentSetted.collectAsState()
        val isActivityFinished by viewModel.isActivityFinished.collectAsState()

		if (!isContentSetted) {
			setContent {
				AppTheme(this, isDarkThemeOn, isFollowingSystemOn, isDynamicColorsOn) {
					settingsScreen()
				}
			}
		}
		
		if (!isActivityFinished) {
			if(isTaskRoot) {
        		startActivity(Intent(this, MainActivity::class.java))
			} else {
				finish()
			}
		}

        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxSize()
        ) {
            BackButton(this, isSoundEffectsOn,viewModel)
            SettingsTopBar()
            SettingsCardsImpl(
				this,
                viewModel,
                isFollowingSystemOn,
                isDarkThemeOn,
                isDynamicColorsOn,
                isSoundEffectsOn,
                isBackgroundMusicOn,
                isAboutShown
            )
        }

        fun syncDarkThemeValue() {
            if (isFollowingSystemOn) {
                if (isSystemInDarkTheme) {
                    viewModel.updateIsDarkThemeOn(true)
                } else {
                    viewModel.updateIsDarkThemeOn(false)
                }
            }
        }
        syncDarkThemeValue()
    }
}