package com.rafael.tictactoe.game.domain.model

sealed interface GameEvent {
    data class getFirstPlayerName(val name: String) : GameEvent
    data class getSecondPlayerName(val name: String) : GameEvent
    object switchPlayerPositions : GameEvent
    object startGame : GameEvent
    data class clickOnCardGrid(val rowIndex: Int, val cardIndex: Int) : GameEvent
    object execEasyModeCPUCardClick : GameEvent
    object resetVictoryCounter : GameEvent
    object playAgain : GameEvent
    object toggleBgMusicButton : GameEvent
    object toggleSoundEffectsButton : GameEvent
    object toggleExpandButton : GameEvent
    object toggleExitDialog : GameEvent
    object exitGame : GameEvent
    object dimissExitDialog : GameEvent
    data class updateIsGotOldSoundPlaying(val value: Boolean) : GameEvent
    data class updateIsWinSoundPlaying(val value: Boolean) : GameEvent
}