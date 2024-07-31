package com.rafael.tictactoe.core.services.music

expect fun backgroundMusic(
    isBMPlaying: Boolean,
    isBackgroundMusicOn: Boolean,
    isAppInOnStop: Boolean = false,
): Boolean?
