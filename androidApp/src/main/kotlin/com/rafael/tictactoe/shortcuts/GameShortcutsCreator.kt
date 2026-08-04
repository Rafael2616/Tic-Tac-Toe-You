package com.rafael.tictactoe.shortcuts

import android.content.Context
import android.content.Intent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.rafael.tictactoe.TicTacToeActivity
import com.rafael.tictactoe.core.resources.R

internal class GameShortcutsCreator {

    data class Shortcut(
        @param:StringRes val shortLabel: Int,
        @param:StringRes val longLabel: Int,
        @param:DrawableRes val icon: Int,
    )

    private val shortcuts = listOf(
        Shortcut(
            shortLabel = R.string.short_label,
            longLabel = R.string.online_match,
            icon = R.drawable.group_24px,
        ),
        Shortcut(
            shortLabel = R.string.short_label,
            longLabel = R.string.vs_cpu,
            icon = R.drawable.memory_24px,
        ),
        Shortcut(
            shortLabel = R.string.short_label,
            longLabel = R.string.play,
            icon = R.drawable.play_arrow_24px,
        ),
    )

    fun create(context: Context) {
        shortcuts.forEachIndexed { index, shortcut ->
            val mShortcut = ShortcutInfoCompat.Builder(context, "dynamic_$index")
                .setShortLabel(context.getString(shortcut.shortLabel))
                .setLongLabel(context.getString(shortcut.longLabel))
                .setIcon(
                    IconCompat.createWithResource(
                        context,
                        shortcut.icon,
                    ),
                )
                .setIntent(
                    Intent(context, TicTacToeActivity::class.java).apply {
                        action = Intent.ACTION_VIEW
                        putExtra(context.getString(shortcut.longLabel), "s$index")
                    },
                )
                .build()
            ShortcutManagerCompat.pushDynamicShortcut(context, mShortcut)
        }
    }
}
