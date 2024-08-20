package com.rafael.tictactoe.core.platform.network

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin

actual val ktorClientEngine: HttpClientEngine = Darwin.create()
