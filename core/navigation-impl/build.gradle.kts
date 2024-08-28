plugins {
    alias(libs.plugins.rafael.library)
    alias(libs.plugins.rafael.library.compose)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.kotlin.serialization)
}

android.namespace = "com.rafael.tictactoe.core.navigation_impl"

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Kotlin
            implementation(libs.kotlinx.serialization.json)
            // Navigation
            implementation(libs.jetbrains.navigation.compose)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.compose.viewmodel)
            implementation(libs.koin.compose)
            // Material Motion
            implementation(libs.material.motion.compose)

            // Projects
            api(projects.core.navigation)
            implementation(projects.core.game)
            implementation(projects.core.platform)
            implementation(projects.core.resources)
            implementation(projects.core.ui)
            implementation(projects.feature.changelogs)
            implementation(projects.feature.game)
            implementation(projects.feature.libraryInfo)
            implementation(projects.feature.main)
            implementation(projects.feature.settings)
            implementation(projects.feature.settingsSearch)
        }
    }
}
