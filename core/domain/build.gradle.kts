plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.android.library.compose)
    alias(libs.plugins.rafael.spotless)
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
