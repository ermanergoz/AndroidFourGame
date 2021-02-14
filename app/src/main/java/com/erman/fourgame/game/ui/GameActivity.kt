package com.erman.fourgame.game.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.erman.fourgame.KEY_GAME_SIZE
import com.erman.fourgame.R
import com.erman.fourgame.databinding.ActivityGameBinding
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GameActivity : AppCompatActivity() {
    private val viewModel: GameViewModel by viewModel{ parametersOf(intent.getIntExtra(KEY_GAME_SIZE, -1))}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityGameBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_game)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
    }
}