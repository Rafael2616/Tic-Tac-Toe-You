package com.rafael.tictactoe.feature.settings.presentation.components.preferences

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalUriHandler
import com.rafael.tictactoe.core.resources.Res
import com.rafael.tictactoe.core.resources.icons.material.Github
import com.rafael.tictactoe.core.resources.view_on_github
import com.rafael.tictactoe.feature.settings.presentation.components.templates.IconPreference

@Composable
fun ViewOnGithubPreference() {
    val uriHandler = LocalUriHandler.current

    IconPreference(
        title = Res.string.view_on_github,
        leadingIcon = Icons.Rounded.Github,
        trailingIcon = Icons.Filled.ArrowOutward,
        onClick = { uriHandler.openUri("https://github.com/Rafael2616/Tic-Tac-Toe-You") },
    )
}
