plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.android.library.compose)
    alias(libs.plugins.rafael.spotless)
}

android.namespace = "com.rafael.tictactoe.core.navigation_impl"

kotlin {
    sourceSets {
        androidMain.dependencies {
            // Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            // Navigation
            implementation(libs.androidx.navigation.compose)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            // Material Motion
            implementation(libs.material.motion.compose)

            // Projects
            implementation(projects.core.ui)
            implementation(projects.core.navigation)
            implementation(projects.feature.changelogs)
            implementation(projects.feature.game)
            implementation(projects.feature.libraryInfo)
            implementation(projects.feature.main)
            implementation(projects.feature.settings)
            implementation(projects.feature.settingsSearch)
        }
    }
}
