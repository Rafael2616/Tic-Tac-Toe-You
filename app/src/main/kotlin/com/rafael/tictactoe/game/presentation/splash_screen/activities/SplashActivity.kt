package com.rafael.tictactoe.game.presentation.splash_screen.activities

import android.content.*
import android.os.Bundle
import androidx.activity.*
import androidx.activity.compose.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.painter.*
import androidx.lifecycle.*
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.ui.theme.AppTheme
import com.rafael.tictactoe.game.presentation.main_screen.activities.MainActivity
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashActivity : ComponentActivity() {
 
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)       
 			setContent{ AppTheme(this){ splashScreen()
			 	val coroutineScope = rememberCoroutineScope()
				 coroutineScope.launch {
 				   delay(1200)
   				 startActivity(Intent(this@SplashActivity, MainActivity::class.java))
    				finish()
				}
			}
		}
	}

	@Composable
	fun splashScreen() {

    val settingsVM = ViewModelProvider(this).get(SettingsViewModel::class.java)	
	val isDarkThemeOn by settingsVM.isDarkThemeOn.collectAsState()

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
	}
}