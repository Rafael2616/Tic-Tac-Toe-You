plugins {
    alias(libs.plugins.rafael.gradle.android.library)
    alias(libs.plugins.rafael.gradle.android.library.compose)
    alias(libs.plugins.rafael.gradle.spotless)
}

android.namespace = "com.rafael.tictactoe.feature.changelogs"

dependencies {
    // Navigation
    implementation(libs.androidx.navigation.compose)
    // Projects
    implementation(projects.core.domain)
    implementation(projects.core.resources)
    implementation(projects.core.navigation)
    implementation(projects.feature.settings)
    implementation(project(":core:ui"))
}
