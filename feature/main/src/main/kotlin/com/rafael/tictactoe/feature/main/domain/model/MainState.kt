package com.rafael.tictactoe.feature.main.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
internal data class MainState(
    val canRedirectToAnGameModeScreen: Boolean = true,
) : Parcelable
