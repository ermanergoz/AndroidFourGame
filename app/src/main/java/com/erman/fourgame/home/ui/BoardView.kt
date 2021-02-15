package com.erman.fourgame.home.ui

import android.content.Context
import android.graphics.Canvas
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
        calculateDimension()
    }

    private fun calculateDimension() {
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

    private fun drawGrid(canvas: Canvas) {
        for (index in 0 until size) {
            val startXVertical = (index * cellWidth).toFloat()
            val startYVertical = 0F
            val endXVertical = (index * cellWidth).toFloat()
            val endYVertical = height.toFloat()
            canvas.drawLine(startXVertical, startYVertical, endXVertical, endYVertical, gridPaint)

            val startXHorizontal = 0F
            val startYHorizontal = (index * cellHeight).toFloat()
            val endXHorizontal = width.toFloat()
            val endYHorizontal = (index * cellHeight).toFloat()
            canvas.drawLine(startXHorizontal, startYHorizontal, endXHorizontal, endYHorizontal, gridPaint)
        }
    }

    private fun drawCellText(canvas: Canvas) {
        textPaint.textSize = calculateFontSize()

        for (column in 0 until size) {
            for (row in 0 until size) {
                canvas.drawText(
                    gridCells[column][row].toString(),
                    (((column * cellWidth) + ((column + 1) * cellWidth)) / 2).toFloat(),
                    ((((row * cellHeight) + ((row + 1) * cellHeight)) / 2) + (cellHeight / 8)).toFloat(),
                    textPaint
                )
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        if (size == 0) return

        drawGrid(canvas)
        drawCellText(canvas)
    }

    init {
        context?.let {
            gridPaint.style = Paint.Style.FILL_AND_STROKE;
            textPaint.style = Paint.Style.FILL_AND_STROKE;
            textPaint.textAlign = Paint.Align.CENTER
            gridPaint.strokeWidth = 10F
        }
    }
}