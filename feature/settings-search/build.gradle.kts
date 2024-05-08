import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.com.android.library)
    alias(libs.plugins.spotless)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.jetbrains.compose)
}

android {
    namespace = "com.rafael.tictactoe.feature.settings_search"
    compileSdk = libs.versions.compileSdk.get().toInt()

    kotlin {
        jvmToolchain {
            languageVersion = JavaLanguageVersion.of(libs.versions.javaVersion.get().toInt())
        }
    }

    buildFeatures {
        compose = true
    }

    tasks.withType<KotlinCompile>().configureEach {
        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
            arg("room.incremental", "true")
            arg("room.generateKotlin", "true")
        }
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
    // App Search
    implementation(libs.androidx.appsearch)
    implementation(libs.androidx.appsearch.localStorage)
    kapt(libs.androidx.appsearch.compiler)
    
    // Projects
    // this is only for while i do not make the all migration
    implementation(project(":feature:settings"))
    implementation(project(":feature:changelogs"))
    implementation(project(":core:ui"))
    implementation(project(":core:resources"))
}
