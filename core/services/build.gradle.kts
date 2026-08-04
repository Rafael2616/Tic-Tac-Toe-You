plugins {
    alias(libs.plugins.rafael.multiplatform.library)
    alias(libs.plugins.rafael.spotless)
}

kotlin {
    android.androidResources.enable = true
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
