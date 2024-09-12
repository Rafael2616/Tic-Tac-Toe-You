package com.rafael.tictactoe.core.platform.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

expect val Dispatchers.IO: CoroutineDispatcher
