package com.erman.fourgame.game.ui

import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erman.fourgame.game.model.GameModel
import com.erman.fourgame.game.model.GridCell

class GameViewModel(private val model: GameModel, private val gameSize: Int) : ViewModel() {
    val gameBoardSize = MutableLiveData<Int>().apply {
        value = gameSize
    }

    private val _gridCells = MutableLiveData<MutableList<MutableList<GridCell>>>().apply {
        value = MutableList(gameSize) { MutableList(gameSize) { GridCell(gridTextNumber = 0) } }
    }
    val gridCells: LiveData<MutableList<MutableList<GridCell>>> = _gridCells

    private val _isPlayersTurn = MutableLiveData<Boolean>().apply {
        value = true
    }
    val isPlayersTurn: LiveData<Boolean> = _isPlayersTurn

    private val _isGameOver = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isGameOver: LiveData<Boolean> = _isGameOver

    private val _score = MutableLiveData<Int>().apply {
        value = 0
    }
    val score: LiveData<Int> = _score

    private val _isReset = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isReset: LiveData<Boolean> = _isReset

    private val _isQuit = MutableLiveData<Boolean>().apply {
        value = false
    }
    val isQuit: LiveData<Boolean> = _isQuit

    fun onTouch(view: View, motionEvent: MotionEvent) {
        val cellWidth = view.width / gameSize
        val cellHeight = view.height / gameSize
        val column = (motionEvent.x / cellWidth).toInt()
        val row = (motionEvent.y / cellHeight).toInt()
        gridCells.value?.let { gridCells ->
            if (gridCells[column][row].gridTextNumber < 4) {
                playersTurn(column, row)
            }
        }
    }

    private fun playersTurn(column: Int, row: Int) {
        isGameOver()
        gridCells.value?.let { gridCells ->
            isPlayersTurn.value?.let { isPlayersTurn ->
                if (isPlayersTurn) {
                    _gridCells.value = model.increaseCells(column, row, gridCells, isPlayersTurn)
                    isGameOver.value?.let { isGameOver ->
                        if (!isGameOver)
                            _isPlayersTurn.value = model.switchTurns(isPlayersTurn)
                    }
                }
            }
            _score.value = model.keepScore(gridCells)
        }
    }

    fun opponentsTurn() {
        isPlayersTurn.value?.let { isPlayer ->
            gridCells.value?.let {
                model.opponentsTurn(it, isPlayer)
            }
            isGameOver()
            isGameOver.value?.let { isGameOver ->
                if (!isGameOver)
                    _isPlayersTurn.value = model.switchTurns(isPlayer)
            }
        }
    }

    private fun isGameOver() {
        gridCells.value?.let {
            _isGameOver.value = model.isGameOver(it)
        }
    }

    fun onResetPressed() {
        gridCells.value?.let {
            _gridCells.value = model.resetGame(it)
        }
        _isPlayersTurn.value = true
        _isReset.value = true
        _score.value = 0
    }

    fun onQuitPressed() {
        _isQuit.value = true
    }
}