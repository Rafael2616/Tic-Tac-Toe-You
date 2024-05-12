plugins {
    alias(libs.plugins.rafael.gradle.library)
    alias(libs.plugins.rafael.gradle.compose)
    alias(libs.plugins.rafael.gradle.spotless)
}

android.namespace = "com.rafael.tictactoe.core.resources"

dependencies {
    // AndroidX
    implementation(libs.androidx.core.splashscreen)
}