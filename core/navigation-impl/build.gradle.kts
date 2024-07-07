plugins {
    alias(libs.plugins.rafael.gradle.android.library)
    alias(libs.plugins.rafael.gradle.android.library.compose)
    alias(libs.plugins.rafael.gradle.spotless)
}

android.namespace = "com.rafael.tictactoe.core.navigation_impl"

dependencies {
    // Navigation
    implementation(libs.androidx.navigation.compose)
    // Material Motion
    implementation(libs.material.motion.compose)
    implementation(libs.androidx.hilt.navigation.compose)

    // Projects
    implementation(projects.core.ui)
    implementation(projects.core.navigation)
    implementation(projects.feature.changelogs)
    implementation(projects.feature.game)
    implementation(projects.feature.libraryInfo)
    implementation(projects.feature.main)
    implementation(projects.feature.settings)
    implementation(projects.feature.settingsSearch)
}
