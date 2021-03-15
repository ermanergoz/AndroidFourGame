package com.erman.fourgame.app.di

import com.erman.fourgame.game.model.GameModel
import com.erman.fourgame.game.ui.GameViewModel
import com.erman.fourgame.home.ui.HomeViewModel
import org.koin.dsl.module
import org.koin.android.viewmodel.dsl.viewModel

val GameModule = module {
    viewModel { HomeViewModel() }

    viewModel { (gameSize : Int) -> GameViewModel(get(), gameSize) }
    factory { GameModel() }
}