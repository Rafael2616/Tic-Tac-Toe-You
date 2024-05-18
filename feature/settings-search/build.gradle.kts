import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.rafael.gradle.android.library)
    alias(libs.plugins.rafael.gradle.android.library.compose)
    alias(libs.plugins.rafael.gradle.spotless)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.parcelize)
}

android {
    namespace = "com.rafael.tictactoe.feature.settings_search"

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

    implementation(libs.google.guava)

    // Projects
    implementation(projects.core.resources)
    implementation(projects.feature.changelogs)
    implementation(projects.feature.settings)
}
