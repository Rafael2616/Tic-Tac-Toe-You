package com.rafael.tictactoe.game.presentation.game_screen.viewmodels

import android.app.Application
import android.content.*
import androidx.lifecycle.*
import kotlinx.coroutines.flow.*
import com.rafael.tictactoe.R


class GameViewModel(application: Application) : AndroidViewModel(application) {

  val genericPlayerName = getApplication<Application>().getString(R.string.player)
  val genericPlayerX = getApplication<Application>().getString(R.string.player_x)
  val genericPlayerO = getApplication<Application>().getString(R.string.player_o)
  val gotOldString = getApplication<Application>().getString(R.string.got_old)
  var player1Name = MutableLiveData("")
  var player2Name = MutableLiveData("")
  var winner = MutableLiveData(false)
  var gotOld = MutableLiveData(false)
  var isPlayer1 = MutableLiveData(true)


fun gameStateToastLogic(): String {
    val playerName = if (!winner.value!!) {
        if (isPlayer1.value!!) player1Name.value ?: "" else player2Name.value ?: ""
    } else {
        if (!isPlayer1.value!!) player1Name.value ?: "" else player2Name.value ?: ""
    }

    if (gotOld.value!!) {
        return gotOldString
    }

    return if (player1Name.value!!.isNullOrBlank() && player2Name.value!!.isNullOrBlank())
        genericPlayerName
    else if (player1Name.value!!.isNotBlank() && player2Name.value!!.isNotBlank())
        playerName ?: genericPlayerName
    else if (player1Name.value!!.isNullOrBlank() && player2Name.value!!.isNotBlank() && isPlayer1.value!!)
        genericPlayerName
    else if (player1Name.value!!.isNullOrBlank() && player2Name.value!!.isNotBlank() && !isPlayer1.value!!)
        player2Name.value ?: genericPlayerName
    else if (player1Name.value!!.isNotBlank() && player2Name.value!!.isNullOrBlank() && isPlayer1.value!!)
        player1Name.value ?: genericPlayerName
    else if (player1Name.value!!.isNotBlank() && player2Name.value!!.isNullOrBlank() && !isPlayer1.value!!)
        genericPlayerName
    else ""
}


}