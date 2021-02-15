package com.erman.fourgame.game.ui

import android.util.Log
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erman.fourgame.game.model.GameModel

class GameViewModel(private val model: GameModel, private val gameSize: Int) : ViewModel() {
    val gameBoardSize = MutableLiveData<Int>().apply {
        value = gameSize
    }

    private val _gridCells = MutableLiveData<MutableList<MutableList<Int>>>().apply {
        value = MutableList(gameSize) { MutableList(gameSize) { 0 } }
    }
    val gridCells: LiveData<MutableList<MutableList<Int>>> = _gridCells

    fun onTouch(view: View, motionEvent: MotionEvent) {
        val cellWidth = view.width / gameSize
        val cellHeight = view.height / gameSize
        val column = (motionEvent.x / cellWidth).toInt()
        val row = (motionEvent.y / cellHeight).toInt()
        Log.e("Clicked on", "$column, $row")
        //gridCells.value?.let { gridCells ->
        //    _gridCells.value = model.makeMove(row, column, gameSize, gridCells)
        //}
    }
}