package com.rafael.tictactoe.game.presentation.game_screen.components.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameState
import com.rafael.tictactoe.game.domain.players_names.PlayerNamesOnCounterHandler

@Composable
fun PlayersNamesOnCounter(state: GameState) {
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)
    val nameOnCounter = PlayerNamesOnCounterHandler()

    nameOnCounter.firstPlayerName(state)
    nameOnCounter.secondPlayerName(state)

     Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = if (isInLandscape) Alignment.TopStart else Alignment.TopCenter
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth(if (isInLandscape) 0.48f else 1f)
                //.statusBarsPadding()
                .padding(top = 20.dp)
        ) {
            Players.values().forEachIndexed { _, player ->
                Box(
                    modifier = Modifier
                        .padding(6.dp)
                        .width(75.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AnimatedContent(
                        targetState = when (player) {
                            Players.FIRST -> state.showPlayer1Name
                            Players.SECOND -> state.showPlayer2Name
                        },
                        transitionSpec = {
                            fadeIn() togetherWith fadeOut()
                        }
                    ) { playerName ->
                        Text(
                            text = playerName,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface,
                            overflow = TextOverflow.Ellipsis,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

enum class Players {
    FIRST,
    SECOND
}