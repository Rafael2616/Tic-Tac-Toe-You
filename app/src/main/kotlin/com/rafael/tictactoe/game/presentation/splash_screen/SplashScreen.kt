package com.rafael.tictactoe.game.presentation.splash_screen

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.theme.AppTheme
import com.rafael.tictactoe.game.presentation.main_screen.activities.MainActivity
import com.rafael.tictactoe.game.domain.util.BackgroundMusic
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController) {
    val settingsVM = viewModel<SettingsViewModel>()
	val isDarkThemeOn by settingsVM.isDarkThemeOn.collectAsState()
	val isBackgroundMusicOn by settingsVM.isBackgroundMusicOn.collectAsState()
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

	BackgroundMusic(context, isBackgroundMusicOn)

	Surface(
    	color = MaterialTheme.colorScheme.surface,
        modifier = Modifier.fillMaxSize()
    ) {
    	Box(
       	 modifier = Modifier
      	 .fillMaxSize(),
       	 contentAlignment = Alignment.Center
    	) {
	    	Image(
                painter = painterResource(id =
	    		if(isDarkThemeOn) R.drawable.splash_icon_dark
                else R.drawable.splash_icon_light),
            	contentDescription = null,
           	 modifier = Modifier
              	 .width(170.dp)
          		 .height(170.dp)
			)
		} 
	}
    scope.launch {
        delay(1200)
        navController.navigate("main")
    }
}