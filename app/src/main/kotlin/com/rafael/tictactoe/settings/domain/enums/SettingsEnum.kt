package com.rafael.tictactoe.settings.domain.enums

sealed class Settings(groupType: GroupType) {
    class Theme : Settings(GroupType.THEME)
    class Sound : Settings(GroupType.SOUND)
    class Customization : Settings(GroupType.CUSTOMIZATION)
    class Other : Settings(GroupType.OTHER)
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
    WINNERLINECOLOR,
    COLORCIRCLE,
    COLORX
}

enum class Other {
    ABOUTAPP
}