/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    alias(libs.plugins.rafael.library)
    alias(libs.plugins.rafael.library.compose)
    alias(libs.plugins.rafael.spotless)
}

android.namespace = "dev.icerock.moko.permissions.compose"

kotlin {
    sourceSets {
        commonMain.dependencies {
            api(projects.libs.mokoPermissionsJvm.permissions)
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.jetbrains.lifecyle.compose)
        }
    }
}
