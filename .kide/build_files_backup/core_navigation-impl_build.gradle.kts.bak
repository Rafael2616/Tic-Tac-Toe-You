plugins {
    alias(libs.plugins.rafael.multiplatform.library)
    alias(libs.plugins.rafael.multiplatform.library.compose)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.kotlin.serialization)
}

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
