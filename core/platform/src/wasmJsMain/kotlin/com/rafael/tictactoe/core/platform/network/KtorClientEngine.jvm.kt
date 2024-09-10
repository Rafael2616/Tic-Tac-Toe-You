package com.rafael.tictactoe.core.platform.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.Js

actual val ktorClientEngine: HttpClientEngine = Js.create()
