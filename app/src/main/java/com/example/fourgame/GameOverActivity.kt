package com.example.fourgame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_game_over.*

class GameOverActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)
        gameOverActivity.setBackgroundColor(Color.parseColor("#AED6F1"))

        val playerScore = intent.getIntExtra("playerScore", 0)
        val opponentScore = intent.getIntExtra("opponentScore", 0)
        val size = intent.getIntExtra("playedSize", 0)

        if(playerScore>opponentScore)
            winnerTextView.text="YOU WON!"
        else
            winnerTextView.text="YOU LOST"

        finalScoreTextView.text = "Your Score: " + playerScore + "\nOpponent: " + opponentScore

        restartButton.setOnClickListener{
            val intent = Intent(this, GameActivity::class.java)
            println(size)
            intent.putExtra("size", size)
            startActivity(intent)
            finish()
        }

        mainMenuButton.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}