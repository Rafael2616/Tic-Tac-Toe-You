package com.rafael.tictactoe.feature.main.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MainState(
    val canRedirectToAnGameModeScreen: Boolean = true,
) : Parcelable
