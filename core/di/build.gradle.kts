plugins {
    alias(libs.plugins.rafael.multiplatform.library)
    alias(libs.plugins.rafael.spotless)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
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
