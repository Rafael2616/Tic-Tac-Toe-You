plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.android.library.compose)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.kotlin.serialization)
}

android.namespace = "com.rafael.tictactoe.feature.game"

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            // Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            // Retrofit
            implementation(libs.retrofit2)
            implementation(libs.retrofit2.converter.kotlinx.serialization)
            implementation(libs.okhttp3)
        }
        commonMain.dependencies {
            // Kotlin
            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.serialization.json)
            implementation(libs.kotlinx.collections.immutable)
            // AndroidX
            implementation(libs.androidx.annotation)
            // Navigation
            implementation(libs.androidx.navigation.compose)
            // Media3
            implementation(libs.androidx.media3.common)
            implementation(libs.androidx.media3.exoplayer)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            // Projects
            implementation(projects.core.ui)
            implementation(projects.core.domain)
            implementation(projects.core.resources)
            implementation(projects.core.platform)
            implementation(projects.feature.settings)
        }
    }
}
