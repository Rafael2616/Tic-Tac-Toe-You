plugins {
    alias(libs.plugins.rafael.multiplatform.library)
    alias(libs.plugins.rafael.multiplatform.library.compose)
    alias(libs.plugins.rafael.spotless)
}

kotlin {
    android.androidResources.enable = true
    sourceSets {
        androidMain.dependencies {
            // AndroidX
            implementation(libs.androidx.core.splashscreen)
        }
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = "com.rafael.tictactoe.core.resources"
    generateResClass = always
}
