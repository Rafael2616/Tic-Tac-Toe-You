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
            implementation(libs.jetbrains.navigation.compose)
            // Koin
            implementation(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            // Projects
            implementation(projects.core.domain)
            implementation(projects.core.navigation)
            implementation(projects.core.platform)
            implementation(projects.core.resources)
            implementation(projects.core.settings)
            implementation(projects.core.ui)
        }
        jvmMain.dependencies {
            implementation(libs.kotlinx.coroutines.swing)
        }
    }
}

compose.resources {
    publicResClass = false
    packageOfResClass = "com.rafael.tictactoe.feature.changelogs"
    generateResClass = always
}
