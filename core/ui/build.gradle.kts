plugins {
    alias(libs.plugins.rafael.gradle.library)
    alias(libs.plugins.rafael.gradle.compose)
    alias(libs.plugins.rafael.gradle.spotless)
}

android.namespace = "com.rafael.tictactoe.core.ui"

dependencies {
    // Dagger Hilt
    implementation(libs.androidx.hilt.navigation.compose)

    // Projects
    implementation(projects.feature.settings)
}
