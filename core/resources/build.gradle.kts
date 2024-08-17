plugins {
    alias(libs.plugins.rafael.library)
    alias(libs.plugins.rafael.library.compose)
    alias(libs.plugins.rafael.spotless)
}

android.namespace = "com.rafael.tictactoe.core.resources"

kotlin {
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
