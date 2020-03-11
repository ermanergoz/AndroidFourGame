package com.example.fourgame

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_custom_grid.*
import kotlinx.android.synthetic.main.activity_main.*

class CustomGridActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //to make it fullscreen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_custom_grid)
        customGridActivity.setBackgroundColor(Color.parseColor("#AED6F1"))


        var customSize=3
        var intent = Intent(this, GameActivity::class.java)

        increaseButton.setOnClickListener {

            if(customSize<19)
                 customSize=customSize+2

            sizeTextView.text=customSize.toString()+"x"+customSize.toString()
        }

        decreaseButton.setOnClickListener {

            if(customSize>3)
                customSize=customSize-2

            sizeTextView.text=customSize.toString()+"x"+customSize.toString()
        }

        startButton.setOnClickListener {
            intent.putExtra("size", customSize)
            startActivity(intent)
            finish()
        }
    }
}