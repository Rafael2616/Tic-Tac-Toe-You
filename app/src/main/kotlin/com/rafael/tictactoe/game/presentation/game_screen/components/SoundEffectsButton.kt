package com.rafael.tictactoe.game.presentation.components

import android.content.Context
import androidx.activity.compose.*
//import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.game.domain.util.StartSoundEffects
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository
import kotlinx.coroutines.launch


@Composable
fun SoundEffectsButton(context: Context, settingsRepo: SettingsRepository, isSoundEffectsOn: Boolean) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .wrapContentSize(),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
                .padding(start = 15.dp)
                .padding(top = 95.dp)
        ) {
            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp)
                    .clickable(onClick = {
                        UiClickSound(context, settingsRepo)
                        scope.launch {
                            StartSoundEffects(settingsRepo)
                        }
                    })
            ) {
                Image(
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(
                        if (isSoundEffectsOn) R.drawable.sound_effects_on_icon
                        else R.drawable.sound_effects_off_icon
                    )
                )
            }
            Text(
                text = stringResource(R.string.active_sound),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}