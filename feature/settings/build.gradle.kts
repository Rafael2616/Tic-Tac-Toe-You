plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.android.library.compose)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.ksp)
}

android.namespace = "com.rafael.tictactoe.feature.settings"

room {
    schemaDirectory("$projectDir/schemas")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            // AndroidX
            implementation(libs.androidx.activity.compose)
            // Room
            implementation(libs.androidx.room.ktx)
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
            // Room
            implementation(libs.androidx.room.runtime)
            // Coil
            api(libs.coil.compose)
            api(libs.coil.network)
            // ColorPicker Compose
            implementation(libs.skydoves.colorpicker.compose)

            // Projects
            api(projects.core.domain)
            api(projects.core.services)
            api(projects.core.resources)
            api(projects.core.navigation)
            api(projects.core.platform)

            // My multiplatform core implementation of smart-tool factory extended colors library
            api(projects.libs.extendedColors)
            // My multiplatform implementation of LazyColumnScrollbar library
            api(projects.libs.lazyColumnScrollbar)
        }
        iosMain.dependencies {
            // Ktor
            implementation(libs.ktor.client.ios)
        }
    }
}

ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
    arg("room.incremental", "true")
    arg("room.generateKotlin", "true")
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
}
