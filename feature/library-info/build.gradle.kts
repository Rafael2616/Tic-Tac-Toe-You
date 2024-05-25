plugins {
    alias(libs.plugins.rafael.gradle.android.library)
    alias(libs.plugins.rafael.gradle.android.library.compose)
    alias(libs.plugins.rafael.gradle.spotless)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android.namespace = "com.rafael.tictactoe.feature.library_info"

dependencies {
    // Kotlin
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.serialization.json)
    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.dagger.hilt.android.compiler)
    // Projects
    implementation(projects.core.resources)
}
