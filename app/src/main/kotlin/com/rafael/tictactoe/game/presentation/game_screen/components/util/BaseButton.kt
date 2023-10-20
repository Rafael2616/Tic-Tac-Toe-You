package com.rafael.tictactoe.game.presentation.game_screen.components.util

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    @DrawableRes btnImage: Int? = null,
    @StringRes btnText: Int? = null,
    btnIcon: ImageVector? = null,
    onClickFun: () -> Unit,
    description: String = "there is no description"
) {
    val settingsVM = hiltViewModel<SettingsViewModel>()
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)

    Box(
        modifier = modifier
            .fillMaxWidth(if (isInLandscape) 0.48f else 1f),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                shape = MaterialTheme.shapes.medium,
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp)
                ),
                modifier = Modifier
                    .size(45.dp)
                    .clip(CircleShape)
                    .clickable(onClick = {
                        settingsVM.execUIClickSound()
                        onClickFun()
                    })
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    if (btnImage != null) {
                        Image(
                            contentDescription = description,
                            modifier = Modifier.fillMaxSize(),
                            painter = painterResource(btnImage)
                        )
                    }
                    if (btnIcon != null) {
                        Icon(
                            imageVector = btnIcon,
                            contentDescription = "Icon",
                            modifier = Modifier
                                .size(38.dp)
                        )
                    }
                }
            }
            if (btnText != null) {
                Text(
                    text = stringResource(btnText),
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(top = 5.dp)
                )
            }
        }
    }
}