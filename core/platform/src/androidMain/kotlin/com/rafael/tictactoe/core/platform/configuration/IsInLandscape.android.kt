package com.rafael.tictactoe.core.platform.configuration

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

actual val isInLandscape: Boolean
    @Composable
    get() = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE
