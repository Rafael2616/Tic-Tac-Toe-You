package com.rafael.tictactoe.feature.libraryinfo.presentation.utils

import android.text.SpannableString
import android.text.style.URLSpan
import android.text.util.Linkify
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration

@Composable
fun annotatedLink(textLink: String): AnnotatedString {
    val spannable = SpannableString(textLink)
    Linkify.addLinks(spannable, Linkify.WEB_URLS)
    val spans = spannable.getSpans(0, textLink.length, URLSpan::class.java)
    val annotatedLink = buildAnnotatedString {
        append(textLink)
        spans.forEach { urlSpan ->
            val start = spannable.getSpanStart(urlSpan)
            val end = spannable.getSpanEnd(urlSpan)
            addStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    textDecoration = TextDecoration.Underline,
                ),
                start = start,
                end = end,
            )
            addStringAnnotation(
                tag = "URL",
                annotation = urlSpan.url,
                start = start,
                end = end,
            )
        }
    }
    return annotatedLink
}
