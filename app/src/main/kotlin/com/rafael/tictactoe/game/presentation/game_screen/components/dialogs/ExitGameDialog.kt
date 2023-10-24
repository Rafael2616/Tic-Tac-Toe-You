package com.rafael.tictactoe.game.presentation.game_screen.components.dialogs

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.model.GameEvent

@Composable
fun ExitGameDialog(
    navController: NavController,
    onEvent: (GameEvent) -> Unit
) {
    var isExitClicked by rememberSaveable { mutableStateOf(false) }
  
    Surface(
        color = Color.Black.copy(alpha = 0.3f),
        modifier = Modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center
        ) {
            Card(
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(1.5.dp, Color.Gray),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(20.dp)
                ),
                modifier = Modifier
                    .width(340.dp)
                    .height(170.dp)
                    .padding(12.dp)
                    .align(Alignment.Center)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.leave_game_msg),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                        .padding(top = 8.dp)
                    )
                    Text(
                        text = stringResource(R.string.leave_game_description),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                        .padding(top = 8.dp)
                    )
                }
                Row(
                    modifier = Modifier
                        .height(90.dp)
                        .fillMaxWidth()
                        .padding(5.dp)
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedButton(
                        onClick = {
                            onEvent(GameEvent.dimissExitDialog)
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(45.dp)
                            .padding(end = 10.dp, bottom  = 5.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.stay),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                    Button(
                        onClick = {
                            if (!isExitClicked) {
                                isExitClicked = true
                                onEvent(GameEvent.exitGame)
                                navController.popBackStack()
                            }
                        },
                        modifier = Modifier
                            .width(100.dp)
                            .height(45.dp)
                            .padding(end = 10.dp, bottom  = 5.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.leave),
                            style = MaterialTheme.typography.bodyMedium,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}