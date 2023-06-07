package com.rafael.tictactoe.game.domain.util

import com.rafael.tictactoe.R
import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.*
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.*

@Composable
fun GotOldSound(
repository: GameRepository,
settingsRepo: SettingsRepository,
context: Context
) = runBlocking {

val gotOldSound = MediaPlayer.create(context, R.raw.got_old_sound)
val isSoundEffectsOn = settingsRepo.getIsSoundEffectsOn()
val isGotOldSoundPlaying = repository.getIsGotOldSoundPlaying()
	
if (isSoundEffectsOn){
    if(!isGotOldSoundPlaying){
        gotOldSound.start()
        repository.setIsGotOldSoundPlaying(true)
        }
    }
}