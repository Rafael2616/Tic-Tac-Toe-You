plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.spotless)
}

android.namespace = "com.rafael.tictactoe.core.di"

kotlin {
    sourceSets {
        androidMain.dependencies {
            // Koin
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            // Projects
            implementation(projects.core.platform)
            implementation(projects.core.services)
            implementation(projects.feature.changelogs)
            implementation(projects.feature.game)
            implementation(projects.feature.libraryInfo)
            implementation(projects.feature.main)
            implementation(projects.feature.settings)
            implementation(projects.feature.settingsSearch)
        }
    }
}
