plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.spotless)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.compose)
}

android {
    namespace = "com.rafael.tictactoe.feature.changelogs"
    compileSdk = libs.versions.compileSdk.get().toInt()

    kotlin {
        jvmToolchain {
            languageVersion = JavaLanguageVersion.of(libs.versions.javaVersion.get().toInt())
        }
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Kotlin
    implementation(libs.kotlinx.coroutines)
    // AndroidX
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Navigation
    implementation(libs.androidx.navigation.compose)
    // Projects
    implementation(project(":core:ui"))
    //implementation(project(":core:navigation"))
    implementation(project(":feature:settings"))
}
