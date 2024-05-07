plugins {
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.spotless)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.jetbrains.compose)
}

android {
    namespace = "com.rafael.tictactoe.feature.game"
    compileSdk = libs.versions.compileSdk.get().toInt()

    kotlin {
        jvmToolchain {
            languageVersion = JavaLanguageVersion.of(libs.versions.javaVersion.get().toInt())
        }
    }

    room {
        schemaDirectory("$projectDir/schemas")
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    // Kotlin
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.collections.immutable)
    // AndroidX
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.hilt.navigation.compose)
    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Navigation
    implementation(libs.androidx.navigation.compose)
    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.dagger.hilt.android.compiler)
    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    // Media3
    implementation(libs.androidx.media3.common)
    implementation(libs.androidx.media3.exoplayer)
     // Compose extended colors
    implementation(libs.smarttoolfactory.extended.colors)
   // LazyColumnScrollbar
    implementation(libs.nanihadesuka.lazyColumnScrollbar)
    // ColorPicker Compose
    implementation(libs.skydoves.colorpicker.compose)
    // Coil
    implementation(libs.coil.compose)
    // App Search
    implementation(libs.androidx.appsearch)

    // Projects
    implementation(project(":core:services"))
    implementation(project(":core:domain"))
    implementation(project(":core:ui"))
    implementation(project(":core:di"))
    implementation(project(":core:resources"))
}
