package com.erman.fourgame.game.model

import kotlin.random.Random

class GameModel {
    fun increaseCells(
        chosenColumn: Int,
        chosenRow: Int,
        gridCells: MutableList<MutableList<GridCell>>,
        turn: Boolean
    ): MutableList<MutableList<GridCell>> {
        if (gridCells[chosenColumn][chosenRow].gridTextNumber < 4) {
            for (column in chosenColumn - 1..chosenColumn + 1) {
                if (column > -1 && column < gridCells.size) {
                    for (row in chosenRow - 1..chosenRow + 1) {
                        if (row > -1 && row < gridCells.size) {
                            increaseCell(column, row, gridCells, turn)
                        }
                    }
                }
            }
        }
        return gridCells
    }

    private fun increaseCell(column: Int, row: Int, gridCells: MutableList<MutableList<GridCell>>, turn: Boolean) {
        if (gridCells[column][row].gridTextNumber < 4)
            gridCells[column][row].gridTextNumber++

        if (gridCells[column][row].gridTextNumber == 4)
            assignPoint(column, row, gridCells, turn)
    }

    fun switchTurns(turn: Boolean): Boolean {
        return !turn
    }

    private fun assignPoint(column: Int, row: Int, gridCells: MutableList<MutableList<GridCell>>, turn: Boolean) {
        if (gridCells[column][row].isPlayersScore == null)
            gridCells[column][row].isPlayersScore = turn
    }

    fun opponentsTurn(gridCells: MutableList<MutableList<GridCell>>, turn: Boolean) {
        var columnIndex = Random.nextInt(0, gridCells.size)
        var rowIndex = Random.nextInt(0, gridCells.size)
        var trialTimeout = 0

        while (gridCells[columnIndex][rowIndex].gridTextNumber == 4 && trialTimeout < 100) {
            columnIndex = Random.nextInt(0, gridCells.size)
            rowIndex = Random.nextInt(0, gridCells.size)
            trialTimeout++
        }
        increaseCells(columnIndex, rowIndex, gridCells, turn)
    }

    fun keepScore(gridCells: MutableList<MutableList<GridCell>>): Int {
        var score = 0
        for (column in 0 until gridCells.size) {
            for (row in 0 until gridCells.size) {
                gridCells[column][row].isPlayersScore?.let {
                    if (it) score++
                }
            }
        }
        return score
    }

    fun isGameOver(gridCells: MutableList<MutableList<GridCell>>): Boolean {
        for (column in 0 until gridCells.size) {
            for (row in 0 until gridCells.size) {
                if (gridCells[column][row].isPlayersScore == null)
                    return false
            }
        }
        return true
    }

    fun resetGame(gridCells: MutableList<MutableList<GridCell>>): MutableList<MutableList<GridCell>> {
        for (column in 0 until gridCells.size) {
            for (row in 0 until gridCells.size) {
                gridCells[column][row].isPlayersScore = null
                gridCells[column][row].gridTextNumber = 0
            }
        }
        return gridCells
    }
}