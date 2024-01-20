package com.rafael.tictactoe.game.mainScreen.domain.model

import android.os.Parcelable
import com.rafael.tictactoe.game.domain.model.GameMode
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class MainState(
    val gameMode: @RawValue GameMode = GameMode.PvP,
) : Parcelable
