plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.android.library.compose)
    alias(libs.plugins.rafael.spotless)
}

android.namespace = "com.rafael.tictactoe.core.platform"

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(libs.kotlinx.datetime)
        }
    }
}
