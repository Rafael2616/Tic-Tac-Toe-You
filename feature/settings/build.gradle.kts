plugins {
    alias(libs.plugins.rafael.android.library)
    alias(libs.plugins.rafael.android.library.compose)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "com.rafael.tictactoe.feature.settings"

    defaultConfig {
        buildConfigField("int", "VERSION_CODE", libs.versions.versionCode.get())
        buildConfigField("String", "VERSION_NAME", "\"${libs.versions.versionName.get()}\"")
    }
    buildFeatures {
        buildConfig = true
    }
    room {
        schemaDirectory("$projectDir/schemas")
    }
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            // AndroidX
            implementation(libs.androidx.activity.compose)
            // Room
            implementation(libs.androidx.room.ktx)
            // App Search
            implementation(libs.androidx.appsearch)
            implementation(libs.androidx.appsearch.localStorage)
            // Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
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
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)
            // Room
            implementation(libs.androidx.room.runtime)
            // Coil
            api(libs.coil.compose)
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

    add("kapt", libs.androidx.appsearch.compiler)
}
