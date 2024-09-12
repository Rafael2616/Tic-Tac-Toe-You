package com.rafael.tictactoe.core.platform.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers


actual val Dispatchers.IO: CoroutineDispatcher
    get() = Dispatchers.IO
