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
            implementation(libs.kotlinx.datetime)
            implementation(libs.jetbrains.viewmodel.compose)
            api(projects.libs.mokoPermissionsJvm.permissions)
            api(projects.libs.mokoPermissionsJvm.permissionsCompose)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.defaults)
            implementation(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            implementation(libs.koin.compose)
            implementation(libs.coil.compose)
            implementation(libs.coil.compose.core)
        }
    }
}
