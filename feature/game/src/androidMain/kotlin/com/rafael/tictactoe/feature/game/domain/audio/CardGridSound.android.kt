package com.rafael.tictactoe.feature.game.domain.audio

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.feature.game.domain.model.GameState
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import org.koin.java.KoinJavaComponent.inject

actual fun cardGridSound(
    state: GameState,
    settingsState: SettingsState,
) {
    val exoPlayer: ExoPlayer by inject(ExoPlayer::class.java)
    val context: Context by inject(Application::class.java)
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
        val clickSound1 =
            MediaItem.fromUri(Uri.parse("android.resource://${context.packageName}/$clickSoundFile"))
        val clickSound2 =
            MediaItem.fromUri(Uri.parse("android.resource://${context.packageName}/$clickSound2File"))

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
