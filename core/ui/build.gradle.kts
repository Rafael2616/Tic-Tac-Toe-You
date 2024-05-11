plugins {
    alias(libs.plugins.rafael.gradle.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.rafael.gradle.spotless)
}

android {
    namespace = "com.rafael.tictactoe.core.ui"

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Dagger Hilt
    implementation(libs.androidx.hilt.navigation.compose)
    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)

    // Projects
    implementation(projects.feature.settings)
}
