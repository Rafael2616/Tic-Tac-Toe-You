plugins {
    alias(libs.plugins.rafael.gradle.android.library)
    alias(libs.plugins.rafael.gradle.android.library.compose)
    alias(libs.plugins.rafael.gradle.spotless)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.parcelize)
}

android.namespace = "com.rafael.tictactoe.feature.main"

dependencies {
    // AndroidX
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.activity.compose)
    // Navigation
    implementation(libs.androidx.navigation.compose)
    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.android.compiler)

    // Projects
    implementation(projects.core.ui)
    implementation(projects.core.resources)
    implementation(projects.core.navigation)
    implementation(projects.feature.settings)
    implementation(projects.feature.changelogs)
}
