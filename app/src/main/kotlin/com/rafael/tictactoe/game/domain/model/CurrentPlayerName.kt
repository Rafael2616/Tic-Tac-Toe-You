package com.rafael.tictactoe.game.domain.model

sealed interface CurrentPlayerName {
    object nameForSetupPhase : CurrentPlayerName
    object nameForSetupPhaseForCPUMode : CurrentPlayerName
    object counterAfterGameStarts : CurrentPlayerName
    object nameForGameInProgress : CurrentPlayerName
    object nameWhenThereWinner : CurrentPlayerName
    object nameWhenGotOld : CurrentPlayerName
}