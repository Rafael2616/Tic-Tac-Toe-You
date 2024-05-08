plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.spotless)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.compose)
}

android {
    namespace = "com.rafael.tictactoe.core.navigation"
    compileSdk = libs.versions.compileSdk.get().toInt()
    
    kotlin {
        jvmToolchain {
            languageVersion = JavaLanguageVersion.of(libs.versions.javaVersion.get().toInt())
        }
    }
}

dependencies {
    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    // Navigation
    implementation(libs.androidx.navigation.compose)
    // Material Motion
    implementation(libs.material.motion.compose)

    // Projects
    implementation(project(":feature:settings"))
    implementation(project(":feature:settings-search"))
    implementation(project(":feature:changelogs"))
    implementation(project(":feature:library-info"))
    implementation(project(":feature:main"))
    implementation(project(":feature:game"))
    implementation(project(":core:ui"))
    implementation(project(":core:navigation"))
}
