package com.rafael.tictactoe.feature.changelogs.di.modules

import com.rafael.tictactoe.feature.changelogs.presentation.viewmodel.ChangelogsViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val changelogsModule: Module = module {
    viewModel { ChangelogsViewModel() }
}
