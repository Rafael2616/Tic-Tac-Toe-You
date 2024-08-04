plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.android.library.compose)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.kotlin.serialization)
}

android.namespace = "com.rafael.tictactoe.feature.library_info"

kotlin {
    sourceSets {
        androidMain.dependencies {
            // Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            // Kotlin
            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.serialization.json)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            // Navigation
            implementation(libs.androidx.navigation.compose)

            // Projects
            implementation(projects.core.resources)
            implementation(projects.core.ui)
        }
    }
}
