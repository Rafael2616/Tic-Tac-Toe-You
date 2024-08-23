package com.rafael.tictactoe.core.platform.configuration

import androidx.compose.runtime.Composable
import androidx.compose.ui.InternalComposeUiApi
import androidx.compose.ui.uikit.LocalKeyboardOverlapHeight
import androidx.compose.ui.unit.dp

@OptIn(InternalComposeUiApi::class)
actual val isImeVisible: Boolean
    @Composable
    get() = LocalKeyboardOverlapHeight.current > 0.dp
