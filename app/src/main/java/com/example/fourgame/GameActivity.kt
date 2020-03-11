package com.example.fourgame

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.*
import android.view.*
import kotlinx.android.synthetic.main.activity_game.*
import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log

class GameActivity : AppCompatActivity() {
    lateinit var buttons: MutableList<Button>
    lateinit var gameEngine: GameEngine
    var screenHeight = 0
    var screenWidth = 0
    var size: Int = 0

    fun createButtons() {
        buttons = mutableListOf()

        for (i in 0 until size * size)  //row
        {
            buttons.add(Button(this))
        }
    }

    fun styleButtons() {
        for (i in 0 until size * size)  //row
        {
            buttons[i].text = 0.toString()
            buttons[i].setBackgroundResource(R.drawable.button_background)
            buttons[i].textSize = screenHeight / (size * 12).toFloat()
            buttons[i].setTextColor(Color.parseColor("#D98880"))
            buttons[i].setTypeface(Typeface.MONOSPACE, Typeface.ITALIC)
        }
    }

    fun convertDpToPx(context: Context, dp: Float): Float {
        return dp * context.getResources().getDisplayMetrics().density
    }

    fun convertPxToDp(context: Context, px: Float): Float {
        return px / context.resources.displayMetrics.density
    }

    fun createGrid() {
        gridLayout.rowCount = size
        gridLayout.columnCount = size
        gridLayout.setBackgroundColor(Color.parseColor("#85C1E9"))

        val displayMetrics = DisplayMetrics()

        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenHeight =
            displayMetrics.heightPixels - convertDpToPx(scoreTextView.context, 35.toFloat()).toInt()
        screenWidth = displayMetrics.widthPixels

        for (i in 0 until size * size) {
            val layoutParams = ViewGroup.LayoutParams(
                (screenWidth) / size, // you can set initial width here
                (screenHeight / size)
            )
            gridLayout.addView(buttons.get(i), layoutParams)
        }
    }

    fun manageButtonListeners() {
        for (i in 0 until size * size) {
            buttons[i].setOnClickListener {
                if (!gameEngine.isGameOver()) {
                    gameEngine.increaseNeighbours(i)
                    gameEngine.keepScore()
                    scoreTextView.text = "Score: " + gameEngine.displayPlayerScore()
                    gameEngine.switchTurns()
                }

                gameEngine.playOpponent()
                displayGameOverActivity()

            }
        }
    }

    fun displayGameOverActivity() {
        if (gameEngine.isGameOver()) {
            Log.d("display game over", "display game over")

            //gameActivity.removeView(scoreTextView)
            var intent = Intent(this, GameOverActivity::class.java)
            intent.putExtra("playerScore", gameEngine.displayPlayerScore())
            intent.putExtra("opponentScore", gameEngine.displayOpponentScore())
            intent.putExtra("playedSize", size)
            startActivity(intent)
            //finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.size = intent.getIntExtra("size", 0)
        //to make it fullscreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        //to hide the title bar
        this.supportActionBar?.hide()
        setContentView(R.layout.activity_game)
        scoreTextView.setBackgroundColor(Color.parseColor("#f0b17a"))
        gameActivity.setBackgroundColor(Color.parseColor("#f0b17a"))
        createButtons()
        gameEngine = GameEngine(buttons, size, true)
        gameEngine.createStats()
        createGrid()
        styleButtons()
        manageButtonListeners()
    }
}