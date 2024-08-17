package com.rafael.tictactoe.feature.game.domain.audio

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.core.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.game.domain.model.GameState
import org.koin.java.KoinJavaComponent.inject

actual fun winnerSound(
    state: GameState,
    settingsState: SettingsState,
): Boolean {
    val exoPlayer: ExoPlayer by inject(ExoPlayer::class.java)
    val context: Context by inject(Application::class.java)
    val winnerSoundFile = R.raw.winner_victory_sound
    val winnerSound =
        MediaItem.fromUri(Uri.parse("android.resource://${context.packageName}/$winnerSoundFile"))
    val isSoundEffectsOn = settingsState.isSoundEffectsOn
    val isWinSoundPlaying = state.isWinSoundPlaying

    if (isSoundEffectsOn && !isWinSoundPlaying) {
        exoPlayer.addMediaItem(winnerSound)
        exoPlayer.prepare()
        exoPlayer.setMediaItem(winnerSound)
        exoPlayer.play()
        return true
    }
    return state.isWinSoundPlaying
}
