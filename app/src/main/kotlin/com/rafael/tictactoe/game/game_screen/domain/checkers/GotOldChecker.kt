package com.rafael.tictactoe.game.domain.checkers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState

@Composable
fun GotOldChecker(
    onEvent: (GameEvent) -> Unit,
    state: GameState,
) {
    val isCardClicked = state.isCardClicked
    val hasWinner = state.hasWinner
    val player1Wins = state.player1Wins
    val player2Wins = state.player2Wins
    val isCounterReset by remember {
        derivedStateOf { player1Wins + player2Wins }
    }

    if ((isCardClicked.flatten().all { it }) && (!hasWinner)) {
        onEvent(GameEvent.UpdateGotOld(true))
    } else {
        onEvent(GameEvent.UpdateGotOld(false))
    }

    if (state.gotOld && !hasWinner) {
        onEvent(GameEvent.GameIsFinished(true))
        if (isCounterReset > 0) {
            onEvent(GameEvent.ShowVictoryCounterReseter(true))
        }
    }
}
