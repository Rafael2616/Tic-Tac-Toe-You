package com.rafael.tictactoe.settings.presentation.settings_screen.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.theme.AppTheme
import com.rafael.tictactoe.common.services.MusicService
import com.rafael.tictactoe.game.presentation.main_screen.activities.MainActivity
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsCardsImpl
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsTopBar
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class SettingsActivity : ComponentActivity() {

    val settingsVM by viewModels<SettingsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme() {
                SettingsScreen()
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
	
    @Composable
    fun SettingsScreen() {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxSize()
        ) {
            SettingsTopBar(this)
        }
    }
}