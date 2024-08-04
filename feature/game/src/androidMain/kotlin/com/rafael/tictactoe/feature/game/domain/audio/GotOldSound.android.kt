package com.rafael.tictactoe.feature.game.domain.audio

import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.core.domain.util.PACKAGE_NAME
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import org.koin.java.KoinJavaComponent.inject

actual fun gotOldSound(
    state: GameState,
    settingsState: SettingsState,
): Boolean {
    val exoPlayer: ExoPlayer by inject(ExoPlayer::class.java)
    val gotOldSoundFile = R.raw.got_old_sound
    val gotOldSound = MediaItem.fromUri(Uri.parse("android.resource://$PACKAGE_NAME/$gotOldSoundFile"))
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
