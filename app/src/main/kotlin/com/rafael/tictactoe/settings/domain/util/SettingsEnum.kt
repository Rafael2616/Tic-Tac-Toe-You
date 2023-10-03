package com.rafael.tictactoe.settings.domain.util

sealed class Settings(groupType: GroupType) {
    class Theme : SettingsEnum(GroupType.THEME)
    class Sound : SettingsEnum(GroupType.SOUND)
    class Customization : SettingsEnum(GroupType.CUSTOMIZATION)
    class Other : SettingsEnum(GroupType.OTHER)
}

enum class Theme {
    FOLLOWSYSTEM,
    DARKTHEME,
    DYNAMICCOLORS
}

enum class Sound {
    SOUNDEFFECTS,
    BACKGROUNDMUSIC
}

enum class Customization {
    WINNERLINECOLOR
}

enum class Other {
    ABOUTAPP
}