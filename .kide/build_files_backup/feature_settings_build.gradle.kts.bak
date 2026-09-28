plugins {
    alias(libs.plugins.rafael.multiplatform.library)
    alias(libs.plugins.rafael.multiplatform.library.compose)
    alias(libs.plugins.rafael.spotless)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines)
            implementation(libs.kotlinx.collections.immutable)
            implementation(libs.androidx.annotation)
            implementation(libs.jetbrains.navigation.compose)
            api(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            implementation(libs.koin.compose)
            api(libs.coil.compose)
            implementation(libs.coil.network)
            implementation(libs.filekit.core)
            implementation(libs.filekit.compose)
            implementation(libs.skydoves.colorpicker.compose)
            implementation(libs.lazyColumn.scrollbar)

            // Projects
            implementation(projects.core.navigation)
            implementation(projects.core.platform)
            implementation(projects.core.resources)
            implementation(projects.core.services)
            api(projects.core.settings)
            implementation(projects.core.ui)

            // My multiplatform core implementation of smart-tool factory extended colors library
            implementation(projects.libs.extendedColors)
        }
    }
}
