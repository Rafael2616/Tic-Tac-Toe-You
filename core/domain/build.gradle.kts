plugins {
    alias(libs.plugins.rafael.gradle.library)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.rafael.gradle.spotless)
}

android {
    namespace = "com.rafael.tictactoe.core.domain"

    defaultConfig {
        buildConfigField("int", "VERSION_CODE", libs.versions.versionCode.get())
        buildConfigField("String", "VERSION_NAME", "\"${libs.versions.versionName.get()}\"")
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(libs.androidx.compose.ui)
}
