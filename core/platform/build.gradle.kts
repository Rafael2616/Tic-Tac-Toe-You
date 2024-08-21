plugins {
    alias(libs.plugins.rafael.library)
    alias(libs.plugins.rafael.library.compose)
    alias(libs.plugins.rafael.spotless)
}

android.namespace = "com.rafael.tictactoe.core.platform"

kotlin {
    sourceSets {
        androidMain.dependencies {
            // AndroidX
            implementation(libs.androidx.activity.compose)
            // Ktor
            implementation(libs.ktor.client.android)
        }
        commonMain.dependencies {
            // Kotlin
            implementation(libs.kotlinx.datetime)
            // AndroidX
            implementation(libs.jetbrains.viewmodel.compose)
            implementation(libs.jetbrains.lifecyle.compose)
            // Moko
            api(libs.moko.permissions)
            api(libs.moko.permissions.compose)
            // Ktor
            api(libs.ktor.client.core)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            implementation(libs.koin.compose)
            // Coil
            api(libs.coil.compose)
            api(libs.coil.compose.core)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
    }
}
