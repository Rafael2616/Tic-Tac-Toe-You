plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.android.library.compose)
    alias(libs.plugins.rafael.spotless)
}

android.namespace = "com.rafael.tictactoe.feature.changelogs"

kotlin {
    sourceSets {
        commonMain.dependencies {
            // Navigation
            implementation(libs.androidx.navigation.compose)
            // Projects
            implementation(projects.core.domain)
            implementation(projects.core.resources)
            implementation(projects.core.navigation)
            implementation(projects.core.ui)
            implementation(projects.feature.settings)
        }
    }
}
