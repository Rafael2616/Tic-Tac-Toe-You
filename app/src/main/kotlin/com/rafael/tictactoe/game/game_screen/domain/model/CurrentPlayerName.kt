package com.rafael.tictactoe.game.domain.model

sealed interface CurrentPlayerName {
    object NameForSetupPhase : CurrentPlayerName
    object NameForSetupPhaseForCPUMode : CurrentPlayerName
    object CounterAfterGameStarts : CurrentPlayerName
    object NameForGameInProgress : CurrentPlayerName
    object NameWhenThereWinner : CurrentPlayerName
    object NameWhenGotOld : CurrentPlayerName
}
