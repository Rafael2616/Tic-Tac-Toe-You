package com.rafael.tictactoe.settings.presentation.settings_screen.components

import android.app.Activity
import android.content.*
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
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.game.presentation.main_screen.activities.MainActivity
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.settings.domain.util.RecreateOrFinishActivityHandler

@Composable
fun BackButton(
	context: Context,
	viewModel: SettingsViewModel,
    activity: Activity,
	isTaskRoot: Boolean
) {

    val isSoundEffectsOn by viewModel.isSoundEffectsOn.collectAsState()

Box(
    modifier = Modifier
  	 .fillMaxSize()
  	 .padding(top = 12.dp, start = 10.dp),
    contentAlignment = Alignment.TopStart
) {
		IconButton(
        	onClick = {
				UiClickSound(context, isSoundEffectsOn)
				RecreateOrFinishActivityHandler(context, activity, viewModel, true/*= isActivityFinished*/, isTaskRoot);
			},
            modifier = Modifier
          	 .width(35.dp).height(35.dp)
	) {
		Image(
			contentDescription = null,
            modifier = Modifier
                .height(35.dp)
				.width(35.dp),
			painter = painterResource(id = R.drawable.back_arrow)
			)
		}
	}
}