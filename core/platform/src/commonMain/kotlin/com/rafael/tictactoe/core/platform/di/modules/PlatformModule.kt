package com.rafael.tictactoe.core.platform.di.modules

import com.rafael.tictactoe.core.platform.permissions.PermissionsViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val platformModule = module {
    viewModel {
        PermissionsViewModel(get())
    }
}
