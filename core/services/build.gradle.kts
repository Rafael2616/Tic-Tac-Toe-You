plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.ksp)
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

            // Projects
            implementation(projects.core.domain)
        }
        commonMain.dependencies {
            // Koin
            api(libs.koin.core)
        }
    }
}
