package com.rafael.tictactoe.settings.presentation.settings_screen.components

import com.rafael.tictactoe.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.*
import com.rafael.tictactoe.settings.domain.util.HandleSwitchClicks
import com.rafael.tictactoe.settings.domain.util.HandleCardText
import com.rafael.tictactoe.settings.domain.util.HandleCardImage
import com.rafael.tictactoe.settings.domain.util.HandleSwitchValues
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.common.domain.util.UiClickSound


@Composable
fun SettingsCards(
index: Int,
viewModel: SettingsViewModel,
isFollowingSystemOn: Boolean,
isDarkThemeOn: Boolean,
isDynamicColorsOn: Boolean,
isSoundEffectsOn: Boolean,
isBackgroundMusicOn: Boolean,
isAboutShown: Boolean,
) {

val UiSound = false //::UiClickSound


    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .width(360.dp)
            .height(if ((index == 5 ) && (isAboutShown)) 190.dp else 70.dp)
            .clickable(onClick = { HandleSwitchClicks(index,viewModel,isFollowingSystemOn,isDarkThemeOn,isDynamicColorsOn,isSoundEffectsOn,isBackgroundMusicOn, isAboutShown)/*; UiSound*/}) 
    ) {
        Box(
            modifier = Modifier
           .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(end = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    contentDescription = null,
                    modifier = Modifier
                    .height(55.dp)
                    .width(55.dp),
                    painter = HandleCardImage(index, isDarkThemeOn,isDynamicColorsOn,isSoundEffectsOn,isBackgroundMusicOn)
                )
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = HandleCardText(index,isAboutShown)
                )
             if(index != 5) {
                Switch(
                    checked = HandleSwitchValues(index,isFollowingSystemOn,isDarkThemeOn,isDynamicColorsOn,isSoundEffectsOn,isBackgroundMusicOn, isAboutShown),
                    onCheckedChange = { HandleSwitchClicks(index,viewModel,isFollowingSystemOn,isDarkThemeOn,isDynamicColorsOn,isSoundEffectsOn,isBackgroundMusicOn, isAboutShown)/*; UiSound*/}
                )
             }
            if(index == 5) {
               Image(
                 contentDescription = null,
                  modifier = Modifier
                 .height(35.dp)   
                 .width(45.dp),
                  painter = painterResource( if (!isAboutShown)R.drawable.expand_down_icon else R.drawable.expand_up_icon )
                 )
             }
          }
       }
    }
 Spacer(modifier = Modifier.height(12.dp))
}