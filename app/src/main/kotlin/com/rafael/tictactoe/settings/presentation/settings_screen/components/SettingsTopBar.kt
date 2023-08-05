package com.rafael.tictactoe.settings.presentation.settings_screen.components

import android.app.Activity
import android.content.*
import com.rafael.tictactoe.R
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.*
import androidx.compose.ui.input.nestedscroll.*
import androidx.compose.ui.unit.*
import com.rafael.tictactoe.common.domain.util.RecreateOrFinishActivityHandler
import com.rafael.tictactoe.common.domain.util.UiClickSound
import com.rafael.tictactoe.settings.presentation.settings_screen.components.SettingsCardsImpl
import com.rafael.tictactoe.settings.presentation.settings_screen.viewmodels.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun SettingsTopBar(
    context: Context,
    viewModel: SettingsViewModel,
    activity: Activity
) {
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
                    IconButton(onClick = {
                    	UiClickSound(context, isSoundEffectsOn)
				        viewModel.updateIsAboutShown(false)
				        RecreateOrFinishActivityHandler(context, activity, viewModel, true/*= isActivityFinished*/)
                    }) {
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
        SettingsCardsImpl(context, viewModel, paddingValues)
    }
}