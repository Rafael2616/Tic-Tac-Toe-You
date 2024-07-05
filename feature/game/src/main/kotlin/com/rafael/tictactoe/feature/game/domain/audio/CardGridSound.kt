package com.rafael.tictactoe.feature.game.domain.audio

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState

internal fun cardGridSound(
    context: Context,
    state: GameState,
    settingsState: SettingsState,
    exoPlayer: ExoPlayer,
) {
    val isPlayer1 = state.isPlayer1
    val isGameFinished = state.isGameFinished
    val isSoundEffectsOn = settingsState.isSoundEffectsOn

    // Avoid click sound if it action is performed by @RandomCardSorter,
    // This is needed due a bug in mentioned component.
    if (isGameFinished) {
        return
    }

    if (isSoundEffectsOn) {
        val clickSoundFile = R.raw.click1_sound
        val clickSound2File = R.raw.click2_sound
        val clickSound1 = MediaItem.fromUri(Uri.parse("android.resource://$context/$clickSoundFile"))
        val clickSound2 = MediaItem.fromUri(Uri.parse("android.resource://$context/$clickSound2File"))

        if (isPlayer1) {
            exoPlayer.addMediaItem(clickSound1)
            exoPlayer.prepare()
            exoPlayer.setMediaItem(clickSound1)
            exoPlayer.play()
        } else {
            exoPlayer.addMediaItem(clickSound2)
            exoPlayer.prepare()
            exoPlayer.setMediaItem(clickSound2)
            exoPlayer.play()
        }
    }
}
