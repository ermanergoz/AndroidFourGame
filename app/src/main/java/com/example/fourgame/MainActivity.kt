package com.example.fourgame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        //to make it fullscreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainActivity.setBackgroundColor(Color.parseColor("#AED6F1"))
        start3x3gameButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("size", 3)
            startActivity(intent)
        }
        start5x5gameButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("size", 5)
            startActivity(intent)
        }
        start7x7gameButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            intent.putExtra("size", 7)
            startActivity(intent)
            finish()
        }
        startCustomGameButton.setOnClickListener {
            val intent = Intent(this, CustomGridActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.game_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.settingsButton)
        {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        if (item.itemId == R.id.creditsButton) {
            val intent = Intent(this, CreditsActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }
}
