plugins {
    alias(libs.plugins.rafael.multiplatform.library)
    alias(libs.plugins.rafael.multiplatform.library.compose)
    alias(libs.plugins.rafael.spotless)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            // AndroidX
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            // Projects
            api(projects.core.settings)
        }
    }
}
