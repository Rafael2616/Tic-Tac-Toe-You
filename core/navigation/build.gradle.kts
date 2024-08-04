plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.kotlin.serialization)
}

android.namespace = "com.rafael.tictactoe.core.navigation"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.serialization.json)
        }
    }
}
