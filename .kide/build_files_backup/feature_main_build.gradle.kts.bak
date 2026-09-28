plugins {
    alias(libs.plugins.rafael.multiplatform.library)
    alias(libs.plugins.rafael.multiplatform.library.compose)
    alias(libs.plugins.rafael.spotless)
}

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
            // Navigation
            implementation(libs.jetbrains.navigation.compose)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            implementation(libs.koin.compose)

            // Projects
            implementation(projects.core.ui)
            implementation(projects.core.resources)
            implementation(projects.core.platform)
            implementation(projects.core.navigation)
            implementation(projects.core.settings)
            implementation(projects.feature.changelogs)
        }
    }
}
