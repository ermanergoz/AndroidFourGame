package com.erman.fourgame.home.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class BoardView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    var size = 0
    private var cellWidth = 0
    private var cellHeight: Int = 0
    private val gridPaint: Paint = Paint()
    private val textPaint: Paint = Paint()
    var gridCells: MutableList<MutableList<Int>> = mutableListOf(mutableListOf())

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        calculateDimensions()
    }

    private fun calculateDimensions() {
        if (size < 1) {
            return
        }
        cellWidth = width / size
        cellHeight = height / size
        invalidate()
    }

    private fun calculateFontSize(): Float {
        val maxFontSize = Math.min(cellHeight, cellWidth)
        return (maxFontSize / 2).toFloat()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.drawColor(Color.WHITE)
        if (size == 0) {
            return
        }
        val fontSize = calculateFontSize()
        textPaint.textSize = fontSize

        for (i in 0 until size) {

            canvas.drawLine(
                (i * cellWidth).toFloat(),
                0f,
                (i * cellWidth).toFloat(),
                height.toFloat(),
                gridPaint
            )
            canvas.drawLine(
                0f,
                (i * cellHeight).toFloat(),
                width.toFloat(),
                (i * cellHeight).toFloat(),
                gridPaint
            )

            for (j in 0 until size) {
                canvas.drawText(
                    gridCells[i][j].toString(),
                    (((i * cellWidth) + ((i + 1) * cellWidth)) / 2).toFloat(),
                    ((((j * cellHeight) + ((j + 1) * cellHeight)) / 2) + (cellHeight / 8)).toFloat(),
                    textPaint
                )
            }
        }
    }

    init {
        context?.let {
            gridPaint.style = Paint.Style.FILL_AND_STROKE;
            textPaint.style = Paint.Style.FILL_AND_STROKE;
            textPaint.textAlign = Paint.Align.CENTER
            gridPaint.strokeWidth = 10f
        }
    }
}