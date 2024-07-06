package com.rafael.tictactoe.feature.settingssearch.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.TipsAndUpdates
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.feature.changelogs.presentation.components.WhatsNewDialog
import com.rafael.tictactoe.feature.settings.domain.model.SettingItem
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.presentation.components.dialogs.BoardCustomizationDialog
import com.rafael.tictactoe.feature.settings.presentation.components.dialogs.ColorSelectionDialog
import com.rafael.tictactoe.feature.settings.presentation.components.templates.PreferenceGroup
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel
import com.rafael.tictactoe.feature.settingssearch.domain.model.SearchSettingsEvent
import com.rafael.tictactoe.feature.settingssearch.presentation.viewmodel.SearchSettingsViewModel

@Composable
@ExperimentalMaterial3Api
@ExperimentalTextApi
fun SearchSettingsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    settingsVM: SettingsViewModel = hiltViewModel(),
    searchSettingsVM: SearchSettingsViewModel = hiltViewModel(),
) {
    val state by settingsVM.state.collectAsStateWithLifecycle()
    val searchState by searchSettingsVM.state.collectAsStateWithLifecycle()
    var expanded by rememberSaveable { mutableStateOf(true) }
    val matchingSettings = searchState.matchingSettings
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Box(
        modifier = modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true },
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .navigationBarsPadding()
                .statusBarsPadding()
                .semantics { traversalIndex = -1f },
            inputField = {
                SearchBarDefaults.InputField(
                    query = searchState.query,
                    onQueryChange = { searchSettingsVM.onEvent(SearchSettingsEvent.QuerySettingsFromRepository(it)) },
                    onSearch = { expanded = false },
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    placeholder = { Text(stringResource(R.string.search_settings)) },
                    leadingIcon = {
                        IconButton(
                            onClick = { navController.popBackStack() },
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                            )
                        }
                    },
                    trailingIcon = {
                        if (searchState.query.isNotEmpty()) {
                            IconButton(
                                onClick = {
                                    searchSettingsVM.onEvent(SearchSettingsEvent.ResetQuery)
                                },
                            ) {
                                Icon(
                                    imageVector = Icons.Outlined.Close,
                                    contentDescription = null,
                                )
                            }
                        }
                    },
                )
            },
            expanded = expanded,
            onExpandedChange = { expanded = it },
            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.5f),
                dividerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
            ),
        ) {
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 16.dp)
                    .pointerInput(Unit) {
                        detectVerticalDragGestures(
                            onVerticalDrag = { _, _ ->
                                focusManager.clearFocus()
                            },
                        )
                        detectTapGestures(
                            onTap = { focusManager.clearFocus() },
                        )
                    }
                    .animateContentSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                item {
                    if (matchingSettings.isNotEmpty()) {
                        PreferenceGroup(R.string.settings) {
                            matchingSettings.forEach { setting ->
                                setting.content?.invoke(settingsVM::onEvent, state, navController)
                            }
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(220.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            AnimatedContent(
                                targetState = if (searchState.query.isNotEmpty()) {
                                    stringResource(R.string.no_matching_settings_msg) + ": ${searchState.query}"
                                } else {
                                    val randomSetting = SettingItem.getSettingsItems(context, state)
                                        .filter { it.isAvailable }
                                        .random()
                                    stringResource(R.string.search_tip) + ": ${randomSetting.searchSpecs.text}"
                                },
                                transitionSpec = {
                                    fadeIn() togetherWith fadeOut()
                                },
                                label = "",
                            ) { textHint ->
                                Column(
                                    modifier = Modifier.fillMaxSize(),
                                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ) {
                                    Icon(
                                        imageVector = if (searchState.query.isNotEmpty()) Icons.Outlined.Info else Icons.Outlined.TipsAndUpdates,
                                        contentDescription = null,
                                        modifier = Modifier.size(35.dp),
                                    )
                                    Text(
                                        text = textHint,
                                        maxLines = 2,
                                        textAlign = TextAlign.Center,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    AnimatedVisibility(state.showColorSelectionDialog) {
        ColorSelectionDialog(settingsVM::onEvent, state)
    }

    AnimatedVisibility(state.showWhatsNewDialog) {
        WhatsNewDialog(
            settingsVM::onEvent,
            navController,
        )
    }

    AnimatedVisibility(state.showBoardCustomizationDialog) {
        BoardCustomizationDialog(settingsVM::onEvent, state)
    }

    DisposableEffect(Unit) {
        onDispose {
            settingsVM.onEvent(SettingsEvent.OnSettingsScreenDestroy)
        }
    }
}
