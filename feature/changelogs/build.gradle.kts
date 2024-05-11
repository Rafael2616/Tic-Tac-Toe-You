plugins {
    alias(libs.plugins.rafael.gradle.library)
    alias(libs.plugins.rafael.gradle.compose)
    alias(libs.plugins.rafael.gradle.spotless)
}

android.namespace = "com.rafael.tictactoe.feature.changelogs"

dependencies {
    // AndroidX
    implementation(libs.androidx.hilt.navigation.compose)
    // Navigation
    implementation(libs.androidx.navigation.compose)

    // Projects
    implementation(projects.core.domain)
    implementation(projects.core.resources)
    implementation(projects.core.navigation)
    implementation(projects.feature.settings)
}
