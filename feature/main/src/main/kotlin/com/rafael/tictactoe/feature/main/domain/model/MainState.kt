package com.rafael.tictactoe.feature.main.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class MainState(
    val gameMode: @RawValue GameMode = GameMode.PvP,
    val canRedirectToAnGameModeScreen: Boolean = true,
) : Parcelable
