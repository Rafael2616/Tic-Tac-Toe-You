plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.spotless)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.compose)
}

android {
    namespace = "com.rafael.tictactoe.core.navigation_impl"
    compileSdk = libs.versions.compileSdk.get().toInt()
    
    kotlin {
        jvmToolchain {
            languageVersion = JavaLanguageVersion.of(libs.versions.javaVersion.get().toInt())
        }
    }
}

dependencies {
    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
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
