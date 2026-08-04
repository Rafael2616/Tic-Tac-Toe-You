import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget

plugins {
    alias(libs.plugins.rafael.multiplatform.library)
    alias(libs.plugins.rafael.multiplatform.library.compose)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    // New KMP Android target DSL
    extensions.configure<KotlinMultiplatformAndroidLibraryTarget>("android") {
        androidResources.enable = true
        withDeviceTest {
            instrumentationRunner = "androidx.test.runner.AndroidJUnit4Runner"
        }
        withHostTest {
            isIncludeAndroidResources = true
        }
    }
    sourceSets {
        androidMain.dependencies {
            // Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            // Kotlin
            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.serialization.json)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            implementation(libs.koin.compose)

            // Projects
            implementation(projects.core.resources)
            implementation(projects.core.ui)
        }
    }
}
