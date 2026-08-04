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
            // Ktor
            implementation(libs.ktor.client.android)
        }
        commonMain.dependencies {
            // Kotlin
            implementation(libs.kotlinx.datetime)
            // AndroidX
            implementation(libs.jetbrains.viewmodel.compose)
            // Moko
            api(projects.libs.mokoPermissionsJvm.permissions)
            api(projects.libs.mokoPermissionsJvm.permissionsCompose)
            // Ktor
            api(libs.ktor.client.core)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            implementation(libs.koin.compose)
            // Coil
            api(libs.coil.compose)
            api(libs.coil.compose.core)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.ios)
        }
        jvmMain.dependencies {
            implementation(libs.ktor.client.android)
        }
        wasmJsMain.dependencies {
            implementation(libs.ktor.client.js)
        }
    }
}
