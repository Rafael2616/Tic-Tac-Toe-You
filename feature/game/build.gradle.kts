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
            // Ktor
            implementation(libs.ktor.client.android)
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
            implementation(libs.koin.core.viewmodel)
            implementation(libs.koin.compose)
            // Ktor
            api(libs.ktor.client.core)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.client.content.negotiation)

            // Projects
            implementation(projects.core.domain)
            implementation(projects.core.game)
            implementation(projects.core.platform)
            implementation(projects.core.resources)
            implementation(projects.core.ui)
            implementation(projects.feature.settings)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
    }
}
