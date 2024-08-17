plugins {
    alias(libs.plugins.rafael.library)
    alias(libs.plugins.rafael.library.compose)
    alias(libs.plugins.rafael.spotless)
}

android.namespace = "com.rafael.tictactoe.feature.settings"

kotlin {
    sourceSets {
        androidMain.dependencies {
            // AndroidX
            implementation(libs.androidx.activity.compose)
            // Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            // Ktor
            implementation(libs.ktor.client.android)
        }
        commonMain.dependencies {
            // Kotlin
            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.collections.immutable)
            // AndroidX
            implementation(libs.androidx.annotation)
            // Navigation
            implementation(libs.androidx.navigation.compose)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            implementation(libs.koin.compose)
            // Coil
            api(libs.coil.compose)
            implementation(libs.coil.network)
            // ColorPicker Compose
            implementation(libs.skydoves.colorpicker.compose)

            // Projects
            implementation(projects.core.navigation)
            implementation(projects.core.platform)
            implementation(projects.core.resources)
            implementation(projects.core.services)
            api(projects.core.settings)
            implementation(projects.core.ui)

            // My multiplatform core implementation of smart-tool factory extended colors library
            implementation(projects.libs.extendedColors)
            // My multiplatform implementation of LazyColumnScrollbar library
            implementation(projects.libs.lazyColumnScrollbar)
        }
        iosMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.ios)
        }
    }
}
