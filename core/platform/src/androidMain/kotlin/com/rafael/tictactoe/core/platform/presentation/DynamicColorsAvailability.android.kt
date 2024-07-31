package com.rafael.tictactoe.core.platform.presentation

import android.os.Build
import androidx.annotation.ChecksSdkIntAtLeast

actual val dynamicColorsAvailability: Boolean
    @ChecksSdkIntAtLeast(api = Build.VERSION_CODES.S)
    get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
