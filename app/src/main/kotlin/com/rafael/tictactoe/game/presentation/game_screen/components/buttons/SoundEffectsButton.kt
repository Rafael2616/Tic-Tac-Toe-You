package com.rafael.tictactoe.game.presentation.game_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun SoundEffectsButton() {
    val settingsVM = viewModel<SettingsViewModel>()
    val context = LocalContext.current
    val isSoundEffectsOn by settingsVM.isSoundEffectsOn.collectAsState()

    Box(
        modifier = Modifier
           .fillMaxSize()
 	 	 .padding(top = 95.dp, start = 15.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
        ) {
            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp)
                    .clickable(onClick = {
                        UiClickSound(context, isSoundEffectsOn)
						settingsVM.updateIsSoundEffectsOn(!isSoundEffectsOn)
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