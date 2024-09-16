package com.rafael.tictactoe.core.platform.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.js.JsClient

actual val ktorClientEngine: HttpClientEngine = JsClient().create()

