package com.rafael.tictactoe.core.platform.presentation

import androidx.compose.runtime.Composable

@Composable
expect fun OnBack(action: () -> Unit)
