package com.erman.fourgame.home.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.erman.fourgame.KEY_GAME_SIZE
import com.erman.fourgame.R
import com.erman.fourgame.databinding.ActivityHomeBinding
import com.erman.fourgame.game.ui.GameActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {
    private val viewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityHomeBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        viewModel.gameSize.observe(this, Observer {
            if (it != 0) {
                val intent = Intent(this, GameActivity::class.java)
                intent.putExtra(KEY_GAME_SIZE, it)
                startActivity(intent)
            }
        })
    }
}
