plugins {
    alias(libs.plugins.rafael.gradle.library)
    alias(libs.plugins.rafael.gradle.compose)
    alias(libs.plugins.rafael.gradle.spotless)
}

android.namespace = "com.rafael.tictactoe.core.navigation_impl"

dependencies {
    // Navigation
    implementation(libs.androidx.navigation.compose)
    // Material Motion
    implementation(libs.material.motion.compose)

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
