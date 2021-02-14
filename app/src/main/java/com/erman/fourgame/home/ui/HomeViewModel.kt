package com.erman.fourgame.home.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    private val _gameSize = MutableLiveData<Int>().apply {
        value = 0
    }
    val gameSize: MutableLiveData<Int> = _gameSize


    fun on3x3GameClicked() {
        gameSize.value = 3
    }

    fun on5x5GameClicked() {
        gameSize.value = 5
    }

    fun on7x7GameClicked() {
        gameSize.value = 7
    }

    fun onCustomGameClicked() {

    }
}