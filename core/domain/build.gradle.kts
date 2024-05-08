plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.spotless)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.compose)
}

android {
    namespace = "com.rafael.tictactoe.core.domain"
    compileSdk = libs.versions.compileSdk.get().toInt()
    
    kotlin {
        jvmToolchain {
            languageVersion = JavaLanguageVersion.of(libs.versions.javaVersion.get().toInt())
        }
    }
}

dependencies {
    implementation(libs.androidx.compose.ui)
    // Projects
}
