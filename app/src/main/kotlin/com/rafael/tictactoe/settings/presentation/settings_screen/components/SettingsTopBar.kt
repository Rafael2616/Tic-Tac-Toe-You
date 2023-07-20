package com.rafael.tictactoe.settings.presentation.settings_screen.components

import android.content.*
import com.rafael.tictactoe.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*

@Composable
fun SettingsTopBar(){

Box (
     modifier = Modifier
    .fillMaxSize()
    .padding(top = 80.dp, start = 20.dp),
    contentAlignment = Alignment.TopStart
 ) {

Column(
      modifier = Modifier.wrapContentSize(),
      verticalArrangement = Arrangement.Top,
      horizontalAlignment = Alignment.CenterHorizontally
  ) {
       Text(
            text = stringResource(id = R.string.settings),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 30.sp),
            color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}