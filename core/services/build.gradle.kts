plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.spotless)
}

android.namespace = "com.rafael.tictactoe.core.services"

kotlin {
    sourceSets {
        androidMain.dependencies {
            // Media3
            implementation(libs.androidx.media3.common)
            implementation(libs.androidx.media3.exoplayer)
            // Koin
            implementation(libs.koin.android)
        }
        commonMain.dependencies {
            // Koin
            api(libs.koin.core)
        }
    }
}
