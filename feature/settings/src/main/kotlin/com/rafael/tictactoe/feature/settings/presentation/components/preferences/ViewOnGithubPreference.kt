package com.rafael.tictactoe.settings.settingsscreen.presentation.components.preferences

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowOutward
import androidx.compose.runtime.Composable
import com.rafael.tictactoe.R
import com.rafael.tictactoe.common.presentation.icons.material.Github
import com.rafael.tictactoe.settings.settingsscreen.presentation.components.templates.IconPreference

@Composable
fun ViewOnGithubPreference() {
    val githubRepoIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Rafael2616/Tic-Tac-Toe-You"))
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { }

    IconPreference(
        title = R.string.view_on_github,
        leadingIcon = Icons.Rounded.Github,
        trailingIcon = Icons.Filled.ArrowOutward,
        onClick = { launcher.launch(githubRepoIntent) },
    )
}
