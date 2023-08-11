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
import com.rafael.tictactoe.game.domain.util.RestartGame
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun RestartButton() {
    val viewModel = viewModel<GameViewModel>()
    val settingsVM = viewModel<SettingsViewModel>()
    val context = LocalContext.current
	val isSoundEffectsOn by settingsVM.isSoundEffectsOn.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.TopEnd
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 55.dp, end = 30.dp)
        ) {
            Card(
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .width(45.dp)
                    .height(45.dp)
                    .clickable(onClick = {
						viewModel.updateIsExpandButtonShown(false)						
                        UiClickSound(context, isSoundEffectsOn)
                        RestartGame(viewModel)
                    })
            ) {
                Image(
                    contentDescription = null,
                    painter = painterResource(R.drawable.reset_icon),
                    modifier = Modifier.wrapContentSize()
                )
            }
            Text(
                text = stringResource(id = R.string.restart),
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}