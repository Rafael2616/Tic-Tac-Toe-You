/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    alias(libs.plugins.rafael.multiplatform.library)
    alias(libs.plugins.rafael.spotless)
}

kotlin {
    android {
        namespace = "dev.icerock.moko.permissions"
    }
    sourceSets {
        androidMain.dependencies {
            implementation(libs.kotlinx.coroutines)
            implementation(libs.androidx.activity.compose)
            implementation(libs.jetbrains.lifecycle.compose)
        }
    }
}
