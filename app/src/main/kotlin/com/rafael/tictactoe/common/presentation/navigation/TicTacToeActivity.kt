package com.rafael.tictactoe

import android.os.Bundle
import androidx.activity.viewModels
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.rafael.tictactoe.common.presentation.navigation.NavGraph
import com.rafael.tictactoe.common.presentation.theme.AppTheme
import com.rafael.tictactoe.game.domain.util.BackgroundMusic
import com.rafael.tictactoe.game.domain.util.GameUtils
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class TicTacToeActivity : ComponentActivity() {

   val settingsVM: SettingsViewModel by viewModels()
   
    @Inject
	lateinit var gameUtil: GameUtils
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
           BackgroundMusic(this, settingsVM.isBackgroundMusicOn.value)

        setContent {
            AppTheme() {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    modifier = Modifier.fillMaxSize()
                ) {
                    NavGraph(this, gameUtil, settingsVM)
                }
            }
        }
    }
}