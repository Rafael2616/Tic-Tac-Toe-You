plugins {
    alias(libs.plugins.rafael.library)
    alias(libs.plugins.rafael.library.compose)
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
            implementation(projects.core.navigation)
            implementation(projects.core.resources)
            implementation(projects.core.settings)
            implementation(projects.core.ui)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.rafael.tictactoe.feature.changelogs"
    generateResClass = always
}
