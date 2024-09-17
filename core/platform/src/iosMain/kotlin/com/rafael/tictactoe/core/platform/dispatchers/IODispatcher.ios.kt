package com.rafael.tictactoe.core.platform.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

actual val Dispatchers.IODispatcher: CoroutineDispatcher
    get() = IO
