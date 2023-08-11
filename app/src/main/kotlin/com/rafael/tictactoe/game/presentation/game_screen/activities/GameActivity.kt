package com.rafael.tictactoe.game.presentation.game_screen.activities

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
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinatesTest
import com.rafael.tictactoe.game.domain.util.WinnerChecker
import com.rafael.tictactoe.game.domain.util.GotOldChecker
import com.rafael.tictactoe.game.domain.util.GotOldSound
import com.rafael.tictactoe.game.domain.util.GameUtils
import com.rafael.tictactoe.game.domain.util.RestartButtonLogic
import com.rafael.tictactoe.game.domain.util.PlayersNamesDisplay
import com.rafael.tictactoe.game.domain.util.ToastNameLogic
import com.rafael.tictactoe.game.domain.util.WinnerLineCoordinates
import com.rafael.tictactoe.game.domain.util.WinnerSound
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GameActivity : ComponentActivity() {
    val viewModel by viewModels<GameViewModel>()
    val settingsVM by viewModels<SettingsViewModel>()

    @Inject
	lateinit var gameUtil: GameUtils
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme() {
                GameScreen()
            }
        }
    }

    @Composable
    fun GameScreen() {
        val cards by viewModel.cards.collectAsState()
        val isCardClicked by viewModel.isCardClicked.collectAsState()
        val play by viewModel.play.collectAsState()
        val isExpandButtonShown by viewModel.isExpandButtonShown.collectAsState()
        val showRestartButton by viewModel.showRestartButton.collectAsState()
        val winner by viewModel.winner.collectAsState()
        val gotOld by viewModel.gotOld.collectAsState()
        val playerName =gameUtil.playerName
        //val scope = rememberCoroutineScope()

		//Below the logic that decides in  which case each function must be called
        //and params needed for then works and update the UI if thats the case

       Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxSize()
        ) {

            //When activity is created
            CardGrid(WinnerLineCoordinatesTest(this))
            ExpandButton()
            VictoryCounter()
            PlayersNamesLayout()
            GameStateToast()
			RestartButtonLogic(viewModel)
            ToastNameLogic()

            if (!play) {
				GetPlayerPrefsCard()      
                playerName.firstPlayerName()
                playerName.secondPlayerName()
			}

            if (play) {
   		     GotOldChecker(viewModel)
                WinnerChecker(viewModel, cards, isCardClicked)
            }

            if (isExpandButtonShown) {
                BackgroundMusicButton()
                SoundEffectsButton()
            }

            if (showRestartButton) {
				RestartButton()
			}

            if (winner) {
                WinnerSound(this, viewModel, settingsVM)
                WinnerLine(WinnerLineCoordinatesTest(this))
                VictoryCounterReseterButton()
                PlayAgainButton()
            }

            if (gotOld) {
                GotOldSound(this, viewModel, settingsVM)					
                VictoryCounterReseterButton()
                PlayAgainButton()
            }
        }
    }
}