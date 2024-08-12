plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.android.library.compose)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.kotlin.parcelize)
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
            implementation(libs.androidx.navigation.compose)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // Projects
            implementation(projects.core.game)
            implementation(projects.core.ui)
            implementation(projects.core.resources)
            implementation(projects.core.platform)
            implementation(projects.core.navigation)
            implementation(projects.feature.settings)
            implementation(projects.feature.changelogs)
        }
    }
}
