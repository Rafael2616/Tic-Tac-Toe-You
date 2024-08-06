package com.rafael.tictactoe.feature.settings.data.utils

import androidx.compose.ui.graphics.ImageBitmap

expect fun ImageBitmap.toByteArray(): ByteArray

expect fun ByteArray.toBitmap(): ImageBitmap
