plugins {
    alias(libs.plugins.rafael.gradle.library)
    alias(libs.plugins.rafael.gradle.compose)
    alias(libs.plugins.rafael.gradle.spotless)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.jetbrains.compose)
}

android.namespace = "com.rafael.tictactoe.feature.main"

dependencies {
    // AndroidX
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Navigation
    implementation(libs.androidx.navigation.compose)
    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.dagger.hilt.android.compiler)

    // Projects
    implementation(projects.core.resources)
    implementation(projects.core.navigation)
    implementation(projects.feature.settings)
    implementation(projects.feature.changelogs)
}
