package com.rafael.tictactoe.game.presentation.splash_screen.activities

import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.ui.theme.AppTheme
import com.rafael.tictactoe.game.presentation.main_screen.activities.MainActivity
import android.content.*
import android.os.Bundle
import android.os.Handler
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.res.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.graphics.painter.*
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel


class SplashActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
     super.onCreate(savedInstanceState)       
         setContent{ AppTheme(this){ splashScreen()
  Handler().postDelayed({
    startActivity(Intent(this, MainActivity::class.java))
    finish()
         }, 1200)
      }
   }
}

@Composable
fun splashScreen() {
  val sharedPreferences = getSharedPreferences("AppSettings", Context.MODE_PRIVATE)
  var isDarkOn = sharedPreferences.getBoolean("isDarkThemeOn", true)
  var isDarkThemeOn by remember {mutableStateOf(isDarkOn)}
  var isSystemDarkTheme = isSystemInDarkTheme()
  var isSystemThemeOn = sharedPreferences.getBoolean("isSystemThemeOn", true)

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
            painter = painterResource(id = if((isDarkThemeOn) ||
           ((isSystemDarkTheme) && (isSystemThemeOn)))
            R.drawable.splash_icon_dark
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