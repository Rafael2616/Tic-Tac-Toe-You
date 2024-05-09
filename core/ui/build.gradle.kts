plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.compose)
}

android {
    namespace = "com.rafael.tictactoe.core.ui"
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
