/*
 * Copyright 2019 IceRock MAG Inc. Use of this source code is governed by the Apache 2.0 license.
 */

plugins {
    alias(libs.plugins.rafael.multiplatform.library)
    alias(libs.plugins.rafael.multiplatform.library.compose)
    alias(libs.plugins.rafael.spotless)
}

kotlin {
    android {
        namespace = "dev.icerock.moko.permissions.compose"
    }
    sourceSets {
        commonMain.dependencies {
            api(projects.libs.mokoPermissionsJvm.permissions)
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
            implementation(libs.jetbrains.lifecycle.compose)
        }
    }
}
