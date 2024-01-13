package com.rafael.tictactoe.settings.searchSettingsScreen.presentation

import android.content.res.Configuration
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.isTraversalGroup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.traversalIndex
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.rafael.tictactoe.settings.searchSettingsScreen.domain.model.SearchSettingsEvent
import com.rafael.tictactoe.settings.searchSettingsScreen.presentation.viewmodels.SearchSettingsViewModel
import com.rafael.tictactoe.settings.settingsScreen.domain.enums.SettingsGroup
import com.rafael.tictactoe.settings.settingsScreen.domain.util.ComponentValidator
import com.rafael.tictactoe.settings.settingsScreen.presentation.viewmodels.SettingsViewModel

@Composable
@ExperimentalMaterial3Api
fun SearchSettingsScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    settingsVM: SettingsViewModel = hiltViewModel(),
    searchSettingsVM: SearchSettingsViewModel = hiltViewModel(),
) {
    val state by settingsVM.state.collectAsState()
    val searchState by searchSettingsVM.state.collectAsState()
    val isAboutShown = state.isAboutShown
    val winnerLineColor by animateColorAsState(state.winnerLineColor)
    val circleColor by animateColorAsState(state.circleColor)
    val xColor by animateColorAsState(state.xColor)
    val componentValidator = ComponentValidator()
    val orientation = LocalConfiguration.current.orientation
    val isInLandscape = (if (orientation == Configuration.ORIENTATION_LANDSCAPE) true else false)
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
    var active by rememberSaveable { mutableStateOf(false) }
    val matchingSettings = searchState.matchingSettings

    Box(
        modifier = modifier
            .fillMaxSize()
            .semantics { isTraversalGroup = true },
    ) {
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .semantics { traversalIndex = -1f },
            query = searchState.query,
            onQueryChange = { searchSettingsVM.onEvent(SearchSettingsEvent.QuerySettingsFromRepository(it)) },
            onSearch = { active = false },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = { Text("Search settings") },
            leadingIcon = { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null) },
            trailingIcon = { Icon(Icons.Outlined.Search, contentDescription = null) },
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 8.dp)
                    .animateContentSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Card(
                    shape = MaterialTheme.shapes.medium,
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(10.dp),
                    ),
                    modifier = Modifier
                        .fillMaxWidth(if (isInLandscape) 0.65f else 0.92f)
                        .clip(RoundedCornerShape(26.dp)),
                ) {
                    if (matchingSettings.size > 0) {
                        matchingSettings.forEachIndexed { index, setting ->
                            val rowHeight = 58.dp by animateDpAsState(
                                if (setting.groupType == SettingsGroup.Others && setting.id = 9 && isAboutShown) 130.dp else 58.dp,
                            )
                            Box(
                                modifier = Modifier
                                    .height(rowHeight)
                                    .fillMaxWidth()
                                    .clickable {
                                        /*toggleSettingsUseCase(
                                            groupType,
                                            index,
                                            state,
                                            onEvent,
                                            launcher,
                                            navController,
                                        )*/
                                    },
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .animateContentSize()
                                        .fillMaxSize()
                                        .padding(horizontal = 10.dp),
                                ) {
                                    if (matchingSettings[index].leadingIcon != null) {
                                        Icon(
                                            imageVector = matchingSettings[index].leadingIcon!!,
                                            contentDescription = "Icon",
                                            tint = MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier
                                                .size(32.dp),
                                        )
                                    }
                                    if (componentValidator.validateCanvas(setting.groupType)) {
                                        Box(
                                            modifier = Modifier
                                                .clip(CircleShape)
                                                .background(Color.Black.copy(alpha = 0.12f))
                                                .size(32.dp)
                                                .align(Alignment.CenterVertically),
                                            contentAlignment = Alignment.Center,
                                        ) {
                                            Box(
                                                modifier = Modifier.size(24.dp),
                                                contentAlignment = Alignment.Center,
                                            ) {
                                                Canvas(
                                                    modifier = Modifier
                                                        .fillMaxSize(),
                                                ) {
                                                    when (index) {
                                                        0 -> drawLine(
                                                            strokeWidth = 5.dp.toPx(),
                                                            start = Offset(x = 24.dp.toPx(), y = 0.dp.toPx()),
                                                            end = Offset(x = 0.dp.toPx(), y = 24.dp.toPx()),
                                                            color = winnerLineColor,
                                                        )
                                                        1 -> drawCircle(
                                                            center = Offset(12.dp.toPx(), 12.dp.toPx()),
                                                            radius = 12.dp.toPx(),
                                                            style = Stroke(width = 5.dp.toPx()),
                                                            color = circleColor,
                                                        )
                                                        2 -> {
                                                            drawLine(
                                                                strokeWidth = 5.dp.toPx(),
                                                                start = Offset(x = 0.dp.toPx(), y = 0.dp.toPx()),
                                                                end = Offset(x = 24.dp.toPx(), y = 24.dp.toPx()),
                                                                color = xColor,
                                                            )
                                                            drawLine(
                                                                strokeWidth = 5.dp.toPx(),
                                                                start = Offset(x = 24.dp.toPx(), y = 0.dp.toPx()),
                                                                end = Offset(x = 0.dp.toPx(), y = 24.dp.toPx()),
                                                                color = xColor,
                                                            )
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    Column(
                                        modifier = Modifier
                                            .width(if (isInLandscape) 350.dp else 240.dp)
                                            .padding(start = 16.dp, end = 10.dp)
                                            .animateContentSize(),
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.Start,
                                    ) {
                                        Text(
                                            style = MaterialTheme.typography.titleMedium,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            text = stringResource(setting.text),
                                            modifier = Modifier.fillMaxWidth(),
                                            textAlign = TextAlign.Start,
                                        )
                            /*if (componentValidator.validateSubText(groupType, index, isAboutShown)) {
                                Text(
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    text = stringResource(R.string.app_version) + " " + getVersionName() + "(" + getVersionCode() + ")",
                                    textAlign = TextAlign.Start,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 8.dp),
                                )
                            }*/
                                    }
                                    if (matchingSettings[index].trailingIcon != null) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize(),
                                            contentAlignment = Alignment.CenterEnd,
                                        ) {
                                            Icon(
                                                imageVector = matchingSettings[index].trailingIcon!!,
                                                contentDescription = "Icon",
                                                tint = MaterialTheme.colorScheme.onSurface,
                                                modifier = Modifier
                                                    .size(32.dp),
                                            )
                                        }
                                    }
                           /* Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.CenterEnd,
                            ) {
                                Switch(
                                    checked = setting.isChecked!!,
                                    onCheckedChange = {
                                        toggleSettingsUseCase(
                                            groupType,
                                            index,
                                            state,
                                            onEvent,
                                        )
                                    },
                                    thumbContent = {
                                        if (setting.isChecked) {
                                            Icon(
                                                imageVector = Icons.Filled.Check,
                                                contentDescription = "Setting Switcher",
                                                modifier = Modifier.size(SwitchDefaults.IconSize),
                                            )
                                        }
                                    },
                                )
                            }*/
                                }
                            }
                            if (index < (matchingSettings.size - 1)) {
                                HorizontalDivider(
                                    thickness = 4.dp,
                                    color = MaterialTheme.colorScheme.surface,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
