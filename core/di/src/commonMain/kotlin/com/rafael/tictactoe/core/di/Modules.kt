package com.rafael.tictactoe.core.di

import com.rafael.tictactoe.core.platform.di.modules.platformModule
import com.rafael.tictactoe.core.services.di.modules.servicesModule
import com.rafael.tictactoe.feature.changelogs.di.modules.changelogsModule
import com.rafael.tictactoe.feature.game.di.modules.gameModule
import com.rafael.tictactoe.feature.libraryinfo.di.modules.libraryInfoModule
import com.rafael.tictactoe.feature.main.di.modules.mainModule
import com.rafael.tictactoe.feature.settings.di.modules.settingsModule
import com.rafael.tictactoe.feature.settingssearch.di.modules.searchSettingsModule

val modules = listOf(
    settingsModule,
    mainModule,
    gameModule,
    libraryInfoModule,
    searchSettingsModule,
    changelogsModule,
    platformModule,
    servicesModule,
)
