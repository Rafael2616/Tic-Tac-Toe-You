package com.rafael.tictactoe.settings.presentation.settings_screen.components.test

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.domain.util.RecreateOrFinishActivityHandler
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.settings.presentation.settings_screen.components.test.SettingsCardsImpl
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel
import com.rafael.tictactoe.game.presentation.main_screen.MainScreen

@Composable
@ExperimentalMaterial3Api
fun SettingsTopBar(navController: NavController, viewModel: SettingsViewModel) {
    val context = LocalContext.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val isSoundEffectsOn by viewModel.isSoundEffectsOn.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(stringResource(id = R.string.settings))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                        	UiClickSound(context, isSoundEffectsOn)
				            viewModel.updateIsAboutShown(false)
                            viewModel.updateIsActivityFinished(false)
                            navController.navigate("main")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar"
                        )
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { paddingValues ->
        SettingsCardsImpl(paddingValues, viewModel)
    }
}