package com.erman.fourgame.game.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.erman.fourgame.utils.KEY_GAME_SIZE
import com.erman.fourgame.R
import com.erman.fourgame.databinding.ActivityGameBinding
import com.erman.fourgame.dialog.GameOverDialog
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

        //If ObserveForever is used in the ViewModel, ViewModel will no longer be lifecycle aware.
        //The code below is a workaround for that problem.
        viewModel.isPlayersTurn.observe(this, Observer {
            if (it) binding.gameBoard.isClickable = true
            else {
                binding.gameBoard.isClickable = false
                viewModel.opponentsTurn()
            }
        })

        viewModel.isGameOver.observe(this, Observer {
            if (it) GameOverDialog(viewModel).show(supportFragmentManager, "")
        })

        viewModel.isReset.observe(this, Observer {
            binding.gameBoard.invalidate()
            binding.gameBoard.isClickable = true
        })

        viewModel.isQuit.observe(this, Observer {
            if (it) finish()
        })
    }
}