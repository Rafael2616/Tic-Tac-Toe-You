package com.rafael.tictactoe.game.presentation.main_screen.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.TicTacToeActivity
import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun MainOptions(navController: NavController) {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val context = LocalContext.current
    var cards by rememberSaveable { mutableStateOf(List<Boolean>(3) { false }) }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        cards.forEachIndexed { index, _ ->
            Card(
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .width(240.dp)
                    .height(65.dp)
                    .clip(RoundedCornerShape(36.dp))
                    .clickable(onClick = {
                        settingsVM.execUIClickSound(context)
                        when (index) {
                            0 -> navController.navigate(Destinations.Game.screen + "/false")
                            1 -> navController.navigate(Destinations.Game.screen + "/true")
                            2 -> navController.navigate(Destinations.Settings.screen)
                        }
                    })
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(6.dp)
                        .padding(end = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp),
                        painter = painterResource(
                            id = when (index) {
                                0 -> R.drawable.play_icon
                                1 -> R.drawable.cpu_icon
                                2 -> R.drawable.settings_icon
                                else -> R.drawable.transparent_image
                            }
                        )
                    )
                    Text(
                        modifier = Modifier.wrapContentSize(),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        overflow = TextOverflow.Ellipsis,
                        textAlign = TextAlign.Center,
                        softWrap = true,
                        text = when (index) {
                            0 -> stringResource(id = R.string.play)
                            1 -> stringResource(id = R.string.vs_cpu)
                            2 -> stringResource(id = R.string.settings)
                            else -> "index error"
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}