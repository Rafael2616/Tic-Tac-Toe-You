package com.rafael.tictactoe.feature.main.di.modules

import com.rafael.tictactoe.feature.main.presentation.viewmodel.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel { MainViewModel() }
}
