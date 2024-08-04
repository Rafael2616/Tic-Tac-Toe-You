package com.rafael.tictactoe.feature.changelogs.presentation.util

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withAnnotation
import androidx.compose.ui.text.withStyle

@Composable
@ExperimentalTextApi
internal fun styledChangelog(changelog: String): AnnotatedString {
    return buildAnnotatedString {
        val keywords = listOf("Added:", "Fixes:", "Improvements:", "Code:", "Others:")
        val textParts = changelog.split("\n")

        textParts.forEachIndexed { index, part ->
            if (keywords.any { part.contains(it) }) {
                withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Bold)) {
                    append(part)
                }
            } else {
                val urlPattern = "https?://\\S+".toRegex()
                val matcherResult = urlPattern.find(part)
                if (matcherResult != null) {
                    val url = matcherResult.value
                    withStyle(
                        style = SpanStyle(color = MaterialTheme.colorScheme.primary, textDecoration = TextDecoration.Underline),
                    ) {
                        withAnnotation(
                            tag = "URL",
                            annotation = url,
                        ) {
                            append(url)
                        }
                    }
                } else {
                    append(part)
                }
            }
            if (index < textParts.size - 1) {
                append("\n")
            }
        }
    }
}
