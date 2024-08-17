package com.rafael.tictactoe.feature.settings.presentation.components.preferences

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImagePainter
import coil3.compose.SubcomposeAsyncImage
import coil3.compose.SubcomposeAsyncImageContent
import com.rafael.tictactoe.core.domain.util.VERSION_CODE
import com.rafael.tictactoe.core.domain.util.getVersionName
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.about_app
import com.rafael.tictactoe.core.resources.app_version
import com.rafael.tictactoe.core.resources.creator_message
import com.rafael.tictactoe.core.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.core.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.templates.IconPreference
import org.jetbrains.compose.resources.stringResource

@Composable
fun AboutAppPreference(
    onEvent: (SettingsEvent) -> Unit,
    state: SettingsState,
    modifier: Modifier = Modifier,
) {
    val rowHeight by animateDpAsState(
        if (state.isAboutShown) 130.dp else 58.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        ),
        label = "",
    )

    IconPreference(
        title = Res.string.about_app,
        description = if (state.isAboutShown) {
            stringResource(Res.string.creator_message) + "\n" + stringResource(Res.string.app_version) + " " +
                getVersionName() + " (" + VERSION_CODE + ")"
        } else {
            null
        },
        rowHeight = rowHeight,
        leadingIcon = Icons.Outlined.Info,
        extraContent = if (state.isAboutShown) {
            {
                SubcomposeAsyncImage(
                    model = "https://avatars.githubusercontent.com/u/93414086?s=96&v=4",
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = modifier
                        .size(45.dp)
                        .clip(CircleShape),
                ) {
                    val painterState by painter.state.collectAsStateWithLifecycle()
                    if (painterState is AsyncImagePainter.State.Success) {
                        SubcomposeAsyncImageContent()
                    } else {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(30.dp),
                            strokeCap = StrokeCap.Round,
                            strokeWidth = 5.dp,
                        )
                    }
                }
            }
        } else {
            null
        },
        trailingIcon = if (state.isAboutShown) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
        onClick = {
            onEvent(SettingsEvent.ShowAboutAppCard(!state.isAboutShown))
        },
    )
}
