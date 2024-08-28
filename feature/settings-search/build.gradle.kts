plugins {
    alias(libs.plugins.rafael.library)
    alias(libs.plugins.rafael.library.compose)
    alias(libs.plugins.rafael.spotless)
}

android.namespace = "com.rafael.tictactoe.feature.settings_search"

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
            // Kotlin
            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.collections.immutable)
            // AndroidX
            implementation(libs.androidx.annotation)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            implementation(libs.koin.compose)
            // Navigation
            implementation(libs.jetbrains.navigation.compose)

            // Projects
            implementation(projects.core.resources)
            implementation(projects.core.settings)
            implementation(projects.core.ui)
            implementation(projects.feature.settings)
        }
    }
}
