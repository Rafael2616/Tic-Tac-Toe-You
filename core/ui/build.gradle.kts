plugins {
    alias(libs.plugins.rafael.gradle.android.library)
    alias(libs.plugins.rafael.gradle.android.library.compose)
    alias(libs.plugins.rafael.gradle.spotless)
}

android.namespace = "com.rafael.tictactoe.core.ui"

dependencies {
    // AndroidX
    implementation(libs.androidx.activity.compose)

    // Projects
    api(projects.feature.settings)
}
