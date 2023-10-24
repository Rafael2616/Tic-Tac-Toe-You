package com.rafael.tictactoe.game.domain.audio

import android.content.Context
import android.net.Uri
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameEvent
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.settings.domain.model.SettingsState

fun WinnerSound(
	context: Context,
    state: GameState,
    settingsState: SettingsState,
    exoPlayer: ExoPlayer,
    onEvent: (GameEvent) -> Unit
) {
	val winnerSoundFile = R.raw.winner_victory_sound
    val winnerSound = MediaItem.fromUri(Uri.parse("android.resource://$context/$winnerSoundFile"))
    val isSoundEffectsOn = settingsState.isSoundEffectsOn
    val isWinSoundPlaying = state.isWinSoundPlaying

	if (isSoundEffectsOn && !isWinSoundPlaying) {
   	 exoPlayer.addMediaItem(winnerSound)
		exoPlayer.prepare()
        exoPlayer.setMediaItem(winnerSound)
		exoPlayer.play()
   	 onEvent(GameEvent.updateIsWinSoundPlaying(true))
	}
}