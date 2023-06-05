package com.rafael.tictactoe.common.domain.util

import android.content.*
import android.media.MediaPlayer
import androidx.compose.runtime.*
import com.rafael.tictactoe.R
import androidx.compose.ui.platform.*

@Composable
fun UiClickSound(
//context: Context,
isSoundEffectsOn: Boolean
) {
   val myContext = LocalContext.current
   val uiSound = remember { MediaPlayer.create(myContext, R.raw.ui_click_sound) }

if (isSoundEffectsOn){
uiSound.start()
} else {
uiSound.stop()
  }
}