// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    alias(libs.plugins.androidx.room).apply(false)
    alias(libs.plugins.com.android.application).apply(false)
    alias(libs.plugins.com.android.library).apply(false)
    alias(libs.plugins.spotless).apply(false)
    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.kotlin.multiplatform).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.kotlin.parcelize).apply(false)
    alias(libs.plugins.jetbrains.compose.compiler).apply(false)
    alias(libs.plugins.jetbrains.compose).apply(false)
    alias(libs.plugins.licensee).apply(false)
}

