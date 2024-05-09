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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.request.ImageRequest
import com.rafael.tictactoe.core.domain.util.getVersionCode
import com.rafael.tictactoe.core.domain.util.getVersionName
import com.rafael.tictactoe.core.resources.R
import com.rafael.tictactoe.feature.settings.domain.model.SettingsEvent
import com.rafael.tictactoe.feature.settings.domain.model.SettingsState
import com.rafael.tictactoe.feature.settings.presentation.components.templates.IconPreference

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
    )

    IconPreference(
        title = R.string.about_app,
        description = if (state.isAboutShown) {
            stringResource(R.string.creator_message) + "\n" + stringResource(R.string.app_version) + " " +
                getVersionName() + " (" + getVersionCode() + ")"
        } else {
            null
        },
        rowHeight = rowHeight,
        leadingIcon = Icons.Outlined.Info,
        extraContent = if (state.isAboutShown) {
            {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("https://avatars.githubusercontent.com/u/93414086?s=96&v=4")
                        .crossfade(true)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = modifier
                        .size(45.dp)
                        .clip(CircleShape),
                ) {
                    if (painter.state is AsyncImagePainter.State.Success) {
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
