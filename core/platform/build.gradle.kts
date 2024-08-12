plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.android.library.compose)
    alias(libs.plugins.rafael.spotless)
}

android.namespace = "com.rafael.tictactoe.core.platform"

kotlin {
    sourceSets {
        androidMain.dependencies {
            // AndroidX
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            // Kotlin
            implementation(libs.kotlinx.datetime)
            // AndroidX
            implementation(libs.androidx.viewmodel.compose)
            // Moko
            api(libs.moko.permissions)
            api(libs.moko.permissions.compose)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            implementation(libs.koin.compose)
            // Coil
            api(libs.coil.compose)
            api(libs.coil.compose.core)
        }
    }
}
