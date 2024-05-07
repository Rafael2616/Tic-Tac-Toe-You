plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.spotless)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.compose)
}

android {
    namespace = "com.rafael.tictactoe.core.di"
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
    implementation(libs.kotlinx.serialization.json)

    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.dagger.hilt.android.compiler)
    // Retrofit
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter.kotlinx.serialization)
    implementation(libs.okhttp3)

    // Projects
}
