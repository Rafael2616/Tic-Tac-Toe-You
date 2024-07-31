plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.android.library.compose)
    alias(libs.plugins.rafael.spotless)
}

android.namespace = "com.rafael.tictactoe.core.ui"

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
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // Projects
            api(projects.feature.settings)
        }
    }
}
