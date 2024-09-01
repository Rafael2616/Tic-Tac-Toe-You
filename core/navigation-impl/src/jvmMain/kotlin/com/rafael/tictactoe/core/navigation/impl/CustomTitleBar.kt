package com.rafael.tictactoe.core.navigation.impl

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.TooltipArea
import androidx.compose.foundation.TooltipPlacement
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.window.WindowDraggableArea
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material.icons.outlined.FilterNone
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.awt.ComposeWindow
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.PointerEventType
import androidx.compose.ui.input.pointer.onPointerEvent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.app_name
import com.rafael.tictactoe.core.ui.theme.AppTheme
import com.rafael.tictactoe.feature.settings.presentation.viewmodel.SettingsViewModel
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun ApplicationScope.CustomTitleBar(window: ComposeWindow, windowScope: WindowScope) {
    var isMinimized by remember { mutableStateOf(window.isMinimized) }
    val settingsViewModel = koinViewModel<SettingsViewModel>()
    val state by settingsViewModel.state.collectAsStateWithLifecycle()
    var exitButtonBackgroundColor by remember { mutableStateOf(Color.Transparent) }
    AppTheme(state = state) {
        with(windowScope) {
            WindowDraggableArea {
                val icons = listOf(
                    TitleBarIcon(
                        onClick = { window.isMinimized = !window.isMinimized },
                        icon = Icons.Default.Remove,
                        tooltip = "Minimizar",
                        color = MaterialTheme.colorScheme.onSurface,
                        titleBarName = TitleBarName.MINIMIZE,
                    ),
                    TitleBarIcon(
                        onClick = {
                            window.placement = if (window.placement == WindowPlacement.Floating) {
                                WindowPlacement.Maximized
                            } else {
                                WindowPlacement.Floating
                            }
                            isMinimized = window.placement == WindowPlacement.Maximized
                        },
                        icon = if (!isMinimized) Icons.Outlined.CheckBoxOutlineBlank else Icons.Outlined.FilterNone,
                        tooltip = "Maximizar",
                        color = MaterialTheme.colorScheme.onSurface,
                        size = 16.dp,
                        titleBarName = TitleBarName.MAXIMIZE,
                    ),
                    TitleBarIcon(
                        onClick = { exitApplication() },
                        icon = Icons.Default.Close,
                        tooltip = "Fechar",
                        color = if (exitButtonBackgroundColor == Color.Red) Color.White else MaterialTheme.colorScheme.onSurface,
                        titleBarName = TitleBarName.CLOSE,
                    ),
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surface)
                        .height(35.dp)
                        .padding(4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
                ) {
                    Image(
                        painter = painterResource("ic_launcher_foreground.png"),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp),
                    )
                    Text(
                        text = stringResource(Res.string.app_name),
                        color = MaterialTheme.colorScheme.onSurface,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        modifier = Modifier.weight(1f),
                    )

                    Row(
                        modifier = Modifier
                            .height(35.dp),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        icons.forEachIndexed { index, icon ->
                            TooltipArea(
                                tooltip = {
                                    Surface(
                                        modifier = Modifier
                                            .shadow(4.dp)
                                            .padding(top = 12.dp),
                                        color = Color(255, 255, 210),
                                        shape = RoundedCornerShape(4.dp),
                                    ) {
                                        Text(
                                            text = icon.tooltip,
                                            fontSize = MaterialTheme.typography.bodySmall.fontSize,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .padding(horizontal = 4.dp)
                                                .padding(bottom = 4.dp),
                                        )
                                    }
                                },
                                delayMillis = 600,
                                tooltipPlacement = TooltipPlacement.CursorPoint(
                                    alignment = Alignment.BottomEnd,
                                    offset = if (index % 2 == 0) DpOffset(
                                        10.dp,
                                        10.dp,
                                    ) else DpOffset.Zero, // tooltip offset
                                ),
                            ) {
                                IconButton(
                                    onClick = icon.onClick,
                                    modifier = icon.modifier
                                        .then(
                                            if (icon.titleBarName == TitleBarName.CLOSE) {
                                                Modifier
                                                    .background(exitButtonBackgroundColor)
                                                    .onPointerEvent(PointerEventType.Move) {
                                                        exitButtonBackgroundColor = Color.Red
                                                    }
                                                    .onPointerEvent(PointerEventType.Exit) {
                                                        exitButtonBackgroundColor =
                                                            Color.Transparent
                                                    }
                                            } else {
                                                Modifier
                                            },
                                        ),
                                ) {
                                    Icon(
                                        imageVector = icon.icon,
                                        contentDescription = icon.tooltip,
                                        tint = icon.color,
                                        modifier = Modifier.size(icon.size),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

data class TitleBarIcon(
    val onClick: () -> Unit = {},
    val icon: ImageVector,
    val tooltip: String,
    val size: Dp = 20.dp,
    val color: Color,
    val titleBarName: TitleBarName,
    val modifier: Modifier = Modifier,
)

enum class TitleBarName {
    MINIMIZE,
    MAXIMIZE,
    CLOSE
}
//modifier = Modifier
//.background(exitButtonBackgroundColor)
//.onPointerEvent(PointerEventType.Move) {
//    exitButtonBackgroundColor = Color.Red
//}
//.onPointerEvent(PointerEventType.Exit) {
//    exitButtonBackgroundColor = Color.Transparent
//},
