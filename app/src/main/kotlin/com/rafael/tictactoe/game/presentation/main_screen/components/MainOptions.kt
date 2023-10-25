package com.rafael.tictactoe.game.presentation.main_screen.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Memory
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.navigation.Destinations
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun MainOptions(navController: NavController) {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    var options by rememberSaveable { mutableStateOf(List<Boolean>(3) { false }) }
    val orientation = LocalConfiguration.current.orientation
    val state by settingsVM.state.collectAsState()

    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(top = if (orientation == Configuration.ORIENTATION_LANDSCAPE) 45.dp else 0.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        options.forEachIndexed { index, _ ->
            Card(
                shape = MaterialTheme.shapes.large,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
                ),
                modifier = Modifier
                    .fillMaxWidth(if (orientation == Configuration.ORIENTATION_LANDSCAPE) 0.35f else 0.6f)
                    .height(65.dp)
                    .clip(RoundedCornerShape(36.dp))
                    .clickable {
                        settingsVM.execUIClickSound()
                        when (index) {
                            0 -> navController.navigate(Destinations.Game.screen + "/false")
                            1 -> navController.navigate(Destinations.Game.screen + "/true")
                            2 -> navController.navigate(Destinations.Settings.screen)
                        }
                    }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(end = 10.dp, start = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                     Icon(
                        imageVector = when (index) {
                            0 -> Icons.Filled.PlayArrow
                            1 -> Icons.Filled.Memory
                            2 -> Icons.Filled.Settings
                            else -> Icons.Filled.Settings
                        },
                        contentDescription = "Icon",
                        tint = when (index) {
                            0 -> if (state.isDarkThemeOn) Color.Green else Color(0xFF299961)
                            1 -> Color.Red
                            else -> MaterialTheme.colorScheme.onSurface
                        },
       		    	 modifier = Modifier
                            .size(42.dp)
                            .padding(end = 3.dp)
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
            if (index <= options.size - 1) {
                Spacer(modifier = Modifier.height(18.dp))
            }
        }
    }
}