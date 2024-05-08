package com.rafael.tictactoe.feature.game.domain.model

sealed interface CurrentPlayerName {
    object NameForSetupPhase : CurrentPlayerName
    object CounterAfterGameStarts : CurrentPlayerName
    object NameForGameInProgress : CurrentPlayerName
    object NameWhenThereWinner : CurrentPlayerName
    object NameWhenGotOld : CurrentPlayerName
}
