package com.rafael.tictactoe.game.presentation.main_screen.activities

import com.rafael.tictactoe.game.presentation.main_screen.components.OptionsButtons
import com.rafael.tictactoe.game.presentation.main_screen.components.TopAppBar
import android.content.*
import android.os.Bundle
//import android.widget.Toast
import com.rafael.tictactoe.common.presentation.ui.theme.AppTheme
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
//import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
   super.onCreate(savedInstanceState)
      setContent{ AppTheme(this) { homeScreen()
      } 
   }
}

@Composable
fun homeScreen() {

Surface(
     color = MaterialTheme.colorScheme.surface,
     modifier = Modifier.fillMaxSize()
 ) {
      TopAppBar()
      OptionsButtons()
      }
   }
}