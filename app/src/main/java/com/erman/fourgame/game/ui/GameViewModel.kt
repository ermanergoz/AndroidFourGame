package com.erman.fourgame.game.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erman.fourgame.game.model.GameModel

class GameViewModel(private val model: GameModel, private val gameSize: Int): ViewModel() {
    val gameBoardSize = MutableLiveData<Int>().apply {
        value = gameSize
    }
}