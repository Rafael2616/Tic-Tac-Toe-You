package com.rafael.tictactoe.game.presentation.components

import android.content.Context
import androidx.activity.compose.*
//import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.game.domain.util.GameStateToastLogic
import com.rafael.tictactoe.game.domain.util.ToastImageLogic
import com.rafael.tictactoe.game.domain.repository.GameRepository

@Composable
fun GameStateToast(context: Context, repository: GameRepository, winner: Boolean, gotOld: Boolean) {
    var currentPlayerName by rememberSaveable { mutableStateOf("") }

    LaunchedEffect(Unit) {
        currentPlayerName = repository.getCurrentPlayerName()
        GameStateToastLogic(repository, context)
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 140.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.wrapContentSize(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "$currentPlayerName",
                        style = MaterialTheme.typography.titleMedium,
                        color = if (!gotOld) MaterialTheme.colorScheme.onSurface
                        else MaterialTheme.colorScheme.error,
                    )
                    if (!gotOld) {
                        Spacer(modifier = Modifier.padding(3.dp))
                        Image(
                            contentDescription = null,
                            painter = painterResource(id = R.drawable.transparent_image)/*ToastImageLogic() */,
                            modifier = Modifier.height(17.dp).width(17.dp)
                        )
                    }
                    if (winner) {
                        Spacer(modifier = Modifier.padding(3.dp))
                        Text(
                            text = stringResource(R.string.winner),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Green
                        )
                    }
                }
            }
        }
    }
}
