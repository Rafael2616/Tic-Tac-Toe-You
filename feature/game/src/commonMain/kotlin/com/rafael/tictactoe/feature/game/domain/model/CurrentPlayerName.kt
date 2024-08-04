package com.rafael.tictactoe.feature.game.domain.model

internal sealed interface CurrentPlayerName {
    data object NameForSetupPhase : CurrentPlayerName
    data object CounterAfterGameStarts : CurrentPlayerName
    data object NameForGameInProgress : CurrentPlayerName
    data object NameWhenThereWinner : CurrentPlayerName
    data object NameWhenGotOld : CurrentPlayerName
}
