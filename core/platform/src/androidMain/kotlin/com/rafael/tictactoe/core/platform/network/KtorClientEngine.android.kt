package com.rafael.tictactoe.core.platform.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.android.Android

actual val ktorClientEngine: HttpClientEngine = Android.create()
