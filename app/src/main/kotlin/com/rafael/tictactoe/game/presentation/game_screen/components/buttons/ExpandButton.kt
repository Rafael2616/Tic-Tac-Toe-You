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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.game.presentation.game_screen.viewmodels.GameViewModel
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun ExpandButton() {
    val viewModel = viewModel<GameViewModel>()
    val settingsVM = viewModel<SettingsViewModel>()
    val context = LocalContext.current
    val isSoundEffectsOn by settingsVM.isSoundEffectsOn.collectAsState()
    val isExpandButtonShown by viewModel.isExpandButtonShown.collectAsState()

    Box(
        modifier = Modifier
  	     .fillMaxSize()
 	      .padding(top = 60.dp, start = 15.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .wrapContentSize()
         ) {
        Card(
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = Color.Transparent),
            modifier = Modifier
                .width(45.dp)
                .height(35.dp)
		    	.clickable(onClick = {
                    UiClickSound(context, isSoundEffectsOn);
                    viewModel.updateIsExpandButtonShown(!isExpandButtonShown)
                })
 	        ) {
		    	Image(
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    painter = painterResource(if (isExpandButtonShown) R.drawable.expand_up_icon
			    	else R.drawable.expand_down_icon)
                )      
            }       
        }
    }
}