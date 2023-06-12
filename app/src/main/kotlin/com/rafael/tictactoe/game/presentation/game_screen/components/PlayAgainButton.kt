package com.rafael.tictactoe.game.presentation.components

import com.rafael.tictactoe.R
import android.content.Context
import androidx.activity.compose.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.painter.*
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.game.domain.repository.GameRepository
import com.rafael.tictactoe.game.domain.util.RestartGame
import com.rafael.tictactoe.game.domain.util.RestartButtonLogic
import com.rafael.tictactoe.settings.domain.repository.SettingsRepository


@Composable
fun PlayAgainButton(
    context: Context,
    repository: GameRepository,
    settingsRepo: SettingsRepository
) {
    RestartButtonLogic()

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 210.dp)
        ) {
            Card(
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .width(75.dp)
                    .height(75.dp)
                    .clickable(onClick = {
                        UiClickSound(context, settingsRepo)
                        RestartGame(repository)
                    })
            ) {
                Image(
                    contentDescription = null,
                    painter = painterResource(R.drawable.reset_icon),
                    modifier = Modifier
                        .width(75.dp)
                )
            }
            Text(
                text = stringResource(id = R.string.play_again),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}