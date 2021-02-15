package com.erman.fourgame.game.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.databinding.DataBindingUtil
import com.erman.fourgame.utils.KEY_GAME_SIZE
import com.erman.fourgame.R
import com.erman.fourgame.databinding.ActivityGameBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import java.lang.Exception

class GameActivity : AppCompatActivity() {
    private val viewModel: GameViewModel by viewModel { parametersOf(intent.getIntExtra(KEY_GAME_SIZE, -1)) }

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityGameBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_game)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.gameBoard.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                try {
                    viewModel.onTouch(view, motionEvent)
                    view.invalidate()
                } catch (err: Exception) {
                    Log.e("BoardView onTouchEvent", err.toString())
                }
            }
            true
        }
    }
}