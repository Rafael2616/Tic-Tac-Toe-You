package com.rafael.tictactoe.core.platform.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val Dispatchers.IODispatcher: CoroutineDispatcher
    get() = Default
