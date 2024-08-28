plugins {
    alias(libs.plugins.rafael.library)
    alias(libs.plugins.rafael.library.compose)
    alias(libs.plugins.rafael.spotless)
}

android.namespace = "com.rafael.tictactoe.feature.main"

kotlin {
    sourceSets {
        androidMain.dependencies {
            // AndroidX
            implementation(libs.androidx.activity.compose)
            // Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            // AndroidX
            implementation(libs.androidx.annotation)
            // Navigation
            implementation(libs.jetbrains.navigation.compose)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            implementation(libs.koin.compose)

            // Projects
            implementation(projects.core.game)
            implementation(projects.core.ui)
            implementation(projects.core.resources)
            implementation(projects.core.platform)
            implementation(projects.core.navigation)
            implementation(projects.core.settings)
            implementation(projects.feature.changelogs)
        }
    }
}
