package com.rafael.tictactoe.settings.presentation.settings_screen.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.settings.domain.util.CardImageHandler
import com.rafael.tictactoe.settings.domain.util.CardTextHandler
import com.rafael.tictactoe.settings.domain.util.SwitchOnCheckedChangeHandler
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun SettingsCards(index: Int) {
    val viewModel = hiltViewModel<SettingsViewModel>()
    val context = LocalContext.current
    val isSystemInDarkTheme = isSystemInDarkTheme()
    val isDarkThemeOn by viewModel.isDarkThemeOn.collectAsState()
    val isFollowingSystemOn by viewModel.isFollowingSystemOn.collectAsState()
    val isDynamicColorsOn by viewModel.isDynamicColorsOn.collectAsState()
    val isBackgroundMusicOn by viewModel.isBackgroundMusicOn.collectAsState()
    val isSoundEffectsOn by viewModel.isSoundEffectsOn.collectAsState()
    val isAboutShown by viewModel.isAboutShown.collectAsState()

    Spacer(modifier = Modifier.height(12.dp))

    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier
            .animateContentSize()
            .width(360.dp)
            .height(if ((index == 5) && (isAboutShown)) 190.dp else 70.dp)
            .clickable(onClick = {
                UiClickSound(context, isSoundEffectsOn)
                SwitchOnCheckedChangeHandler(
                    context,
                    index,
                    viewModel,
					isSystemInDarkTheme
                )
            }
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxSize().padding(end = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    contentDescription = null,
                    modifier = Modifier
                        .height(55.dp)
                        .width(55.dp),
                    painter = CardImageHandler(index, viewModel)
                )
                Text(
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    text = CardTextHandler(index)
                )
                if (index != 5) {
                    Switch(
                        checked = 
							when (index) {
    							0 -> isFollowingSystemOn
     						   1 -> isDarkThemeOn
   						     2 -> isDynamicColorsOn
    						    3 -> isSoundEffectsOn
      						  4 -> isBackgroundMusicOn
								5 -> isAboutShown
						 	else -> false
							},
                        onCheckedChange = {
                            SwitchOnCheckedChangeHandler(
                                context,
                                index,
                                viewModel,
								isSystemInDarkTheme
                            )
                            UiClickSound(context, isSoundEffectsOn)
                        },
						thumbContent = {
    					  Icon(
     					   imageVector = when (index) {
    							0 -> if (isFollowingSystemOn) Icons.Filled.Check else Icons.Filled.Close
     						   1 -> if (isDarkThemeOn) Icons.Filled.Check else Icons.Filled.Close
   						     2 -> if (isDynamicColorsOn) Icons.Filled.Check else Icons.Filled.Close
    						    3 -> if (isSoundEffectsOn) Icons.Filled.Check else Icons.Filled.Close
      						  4 -> if (isBackgroundMusicOn) Icons.Filled.Check else Icons.Filled.Close
								5 -> if (isAboutShown) Icons.Filled.Check else Icons.Filled.Close
						 	else -> Icons.Filled.Close
							},
    					    contentDescription = null,
       					 modifier = Modifier.size(SwitchDefaults.IconSize)
      					)
   					 }
                    )
                }
                if (index == 5) {
                    Image(
                        contentDescription = null,
                        modifier = Modifier
                            .height(35.dp)
                            .width(45.dp),
                        painter = painterResource(if (!isAboutShown) R.drawable.expand_down_icon else R.drawable.expand_up_icon)
                    )
                }
            }
        }
    }
}