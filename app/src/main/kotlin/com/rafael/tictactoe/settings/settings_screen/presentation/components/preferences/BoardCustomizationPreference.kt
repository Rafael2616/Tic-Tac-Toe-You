package com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.rafael.tictactoe.R
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsEvent
import com.rafael.tictactoe.settings.settingsScreen.domain.model.SettingsState
import com.rafael.tictactoe.settings.settingsScreen.presentation.util.BoardConstants.SHAPE_CUSTOM
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.templates.IconPreference
import kotlin.math.roundToInt

@Composable
fun BoardCustomizationPreference(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
    modifier: Modifier = Modifier,
) {
    val rgbGradientBrush = Brush.linearGradient(
        colors = listOf(Color.Red, Color.Yellow),
    )

    IconPreference(
        title = R.string.board_customization,
        leadingWidget = {
            Box(
                modifier = modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Color.Black.copy(alpha = 0.2f))
                    .fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Card(
                    shape = RoundedCornerShape((state.boardCardsCornerRadius / 4).dp),
                    border = if (state.boardCardsThickness != 0f && state.shapeSelected == SHAPE_CUSTOM) {
                        if (state.isRGBEffectOn) {
                            BorderStroke((state.boardCardsThickness / 2).dp, rgbGradientBrush)
                        } else {
                            BorderStroke((state.boardCardsThickness / 2).dp, state.boardCardsBorderColor)
                        }
                    } else {
                        null
                    },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(state.boardCardsElevation.dp),
                    ),
                    modifier = Modifier
                        .size(28.dp)
                        .then(
                            if (state.boardCardsShadow != 0f) {
                                Modifier.shadow(state.boardCardsShadow.roundToInt().dp, RoundedCornerShape((state.boardCardsCornerRadius / 4).dp))
                            } else {
                                Modifier
                            },
                        ),
                ) { /* No content here */ }
            }
        },
        trailingIcon = Icons.AutoMirrored.Filled.ArrowForward,
        onClick = {
            onEvent(SettingsEvent.ShowBoardCustomizationDialog(true))
        },
    )
}
