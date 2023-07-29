package com.rafael.tictactoe.game.presentation.game_screen.activities

import android.content.*
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.lifecycle.*
import com.rafael.tictactoe.common.presentation.ui.theme.AppTheme
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.game.presentation.game_screen.components.BackgroundMusicButton
import com.rafael.tictactoe.game.presentation.game_screen.components.CardGrid
import com.rafael.tictactoe.game.presentation.game_screen.components.ExpandButton
import com.rafael.tictactoe.game.presentation.game_screen.components.GameStateToast
import com.rafael.tictactoe.game.presentation.game_screen.components.GetPlayerPrefsCard
import com.rafael.tictactoe.game.presentation.game_screen.components.PlayAgainButton
import com.rafael.tictactoe.game.presentation.game_screen.components.PlayersNamesLayout
import com.rafael.tictactoe.game.presentation.game_screen.components.RestartButton
import com.rafael.tictactoe.game.presentation.game_screen.components.SoundEffectsButton
import com.rafael.tictactoe.game.presentation.game_screen.components.VictoryCounter
import com.rafael.tictactoe.game.presentation.game_screen.components.VictoryCounterReseterButton
import com.rafael.tictactoe.game.presentation.game_screen.components.WinnerLine
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.util.WinnerChecker
import com.rafael.tictactoe.game.domain.util.GotOldChecker
import com.rafael.tictactoe.game.domain.util.GotOldSound
import com.rafael.tictactoe.game.domain.util.GameUtils
import com.rafael.tictactoe.game.domain.util.RestartButtonLogic
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.WinnerSound
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameActivity : ComponentActivity() {

    @Inject lateinit var gameUtil: GameUtils
	
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(this) {
                GameScreen()
            }
        }
    }

    @Composable
    fun GameScreen() {
	
	    val settingsVM = ViewModelProvider(this).get(SettingsViewModel::class.java)
        val viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        val cards by viewModel.cards.collectAsState()
        val isCardClicked by viewModel.isCardClicked.collectAsState()
        val play by viewModel.play.collectAsState()
        val expandButton by viewModel.expandButton.collectAsState()
        val showRestartButton by viewModel.showRestartButton.collectAsState()
        val isWinSoundPlaying by viewModel.isWinSoundPlaying.collectAsState()
        val isGotOldSoundPlaying by viewModel.isGotOldSoundPlaying.collectAsState()
        val isSoundEffectsOn by settingsVM.isSoundEffectsOn.collectAsState()
        val winner by viewModel.winner.collectAsState()
        val gotOld by viewModel.gotOld.collectAsState()
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

            //When activity is created
            CardGrid(this, viewModel, settingsVM, gameUtil.coordinate)
            ExpandButton(this, viewModel, settingsVM)
            VictoryCounter(viewModel)
            PlayersNamesLayout(this, viewModel, gameUtil.playerName)
            GameStateToast(this, viewModel)
			RestartButtonLogic(viewModel, isCardClicked, winner, gotOld)

            if (!play) {
				GetPlayerPrefsCard(this, viewModel, settingsVM)
			}
            if (play) {
   		     GotOldChecker(viewModel, isCardClicked, winner)
                WinnerChecker(viewModel, cards, isCardClicked)
            }
            if (expandButton) {
                BackgroundMusicButton(this, settingsVM)
                SoundEffectsButton(this, settingsVM)
            }
            if (showRestartButton) {
				RestartButton(this, viewModel, settingsVM)
			}
            if (winner) {
                WinnerSound(this, viewModel, isSoundEffectsOn, isWinSoundPlaying)
                WinnerLine(viewModel, gameUtil.coordinate)
                VictoryCounterReseterButton(this, viewModel, settingsVM)
                PlayAgainButton(this, viewModel, settingsVM)
            }
            if (gotOld) {
                GotOldSound(this, viewModel, isSoundEffectsOn, isGotOldSoundPlaying)					
                VictoryCounterReseterButton(this, viewModel, settingsVM)
                PlayAgainButton(this, viewModel, settingsVM)
            }
			
	   	if(!isContentSetted) {
		   	setContent{
			   	AppTheme(this, isDarkThemeOn, isFollowingSystemOn, isDynamicColorsOn) {
				   	GameScreen()
					}
				}
			}
        }
    }
}