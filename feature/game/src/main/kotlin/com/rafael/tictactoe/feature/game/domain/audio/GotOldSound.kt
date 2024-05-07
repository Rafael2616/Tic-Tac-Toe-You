package com.rafael.tictactoe.game.domain.audio

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState

fun gotOldSound(
    context: Context,
    state: GameState,
    settingsState: SettingsState,
    exoPlayer: ExoPlayer,
): Boolean {
    val gotOldSoundFile = R.raw.got_old_sound
    val gotOldSound = MediaItem.fromUri(Uri.parse("android.resource://$context/$gotOldSoundFile"))
    val isSoundEffectsOn = settingsState.isSoundEffectsOn
    val isGotOldSoundPlaying = state.isGotOldSoundPlaying

    if (isSoundEffectsOn && !isGotOldSoundPlaying) {
        exoPlayer.addMediaItem(gotOldSound)
        exoPlayer.prepare()
        exoPlayer.setMediaItem(gotOldSound)
        exoPlayer.play()
        return true
    }
    return state.isGotOldSoundPlaying
}
