package com.rafael.tictactoe.game.presentation.main_screen.components.test

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.TicTacToeActivity
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.game.presentation.game_screen.activities.GameActivity
import com.rafael.tictactoe.settings.presentation.settings_screen.activities.SettingsActivity
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun OptionsButtons(navController: NavController, settingsVM: SettingsViewModel) {
 //   val settingsVM = viewModel<SettingsViewModel>()
    val isSoundEffectsOn by settingsVM.isSoundEffectsOn.collectAsState()
    val context = LocalContext.current
    var cards by rememberSaveable { mutableStateOf(List<Boolean>(2) { false }) }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(cards.size) { index ->
            Card(
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .width(240.dp)
                    .height(65.dp)
                    .clickable(onClick = {
                        UiClickSound(context, isSoundEffectsOn)
                        when (index) {
                            0 -> navController.navigate("game")//context.startActivity(Intent(context, GameActivity::class.java))
                            1 -> navController.navigate("settings")//context.startActivity(Intent(context, SettingsActivity::class.java))
                        }
                    })
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 18.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Image(
                            contentDescription = null,
                            modifier = Modifier
                                .height(60.dp)
                                .width(60.dp),
                            painter = painterResource(
                                id = when (index) {
                                    0 -> R.drawable.play_icon
                                    1 -> R.drawable.settings_icon
                                    else -> R.drawable.transparent_image
                                }
                            )
                        )
                        Text(
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            overflow = TextOverflow.Ellipsis,
                            softWrap = true,
                            text = when (index) {
                                0 -> stringResource(id = R.string.play)
                                1 -> stringResource(id = R.string.settings)
                                else -> "index error"
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
