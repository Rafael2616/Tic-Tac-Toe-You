plugins {
    alias(libs.plugins.rafael.gradle.android.library)
    alias(libs.plugins.rafael.gradle.spotless)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
}

android.namespace = "com.rafael.tictactoe.core.services"

dependencies {
    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    ksp(libs.dagger.hilt.android.compiler)
    // Media3
    implementation(libs.androidx.media3.common)
    implementation(libs.androidx.media3.exoplayer)
}
