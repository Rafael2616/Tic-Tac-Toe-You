package com.rafael.tictactoe.game.presentation.main_screen.components

import com.rafael.tictactoe.R
import androidx.activity.ComponentActivity
import androidx.activity.compose.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.*
import androidx.compose.runtime.livedata.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import androidx.compose.ui.text.style.*


@Composable
fun TopAppBar() {
Box (
    modifier = Modifier
   .fillMaxSize()
   .padding(top = 45.dp),
    contentAlignment = Alignment.TopCenter
 ) {
   Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
   ) {
       Text(
            text = stringResource(id = R.string.inapp_title),
            style = MaterialTheme.typography.titleLarge.copy(fontSize = 26.sp),
            color = MaterialTheme.colorScheme.onSurface,
            )
     Spacer(
            modifier = Modifier.height(3.dp)
            )
       Text(
            text = ("Material You"),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
           )
       }
    }
}