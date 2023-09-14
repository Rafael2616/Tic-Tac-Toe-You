package com.rafael.tictactoe.game.presentation.splash_screen.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun SplashImage() {
    val settingsVM = hiltViewModel<SettingsViewModel>()
	val isDarkThemeOn by settingsVM.isDarkThemeOn.collectAsState()

	Box(
        modifier = Modifier
           .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
		Image(
            painter = painterResource(id =
	    	if (isDarkThemeOn) R.drawable.splash_icon_dark
            else R.drawable.splash_icon_light),
        	contentDescription = null,
       	 modifier = Modifier
          	 .width(170.dp)
          	 .height(170.dp)
		)
	} 
<<<<<<< HEAD
}
=======
}
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
