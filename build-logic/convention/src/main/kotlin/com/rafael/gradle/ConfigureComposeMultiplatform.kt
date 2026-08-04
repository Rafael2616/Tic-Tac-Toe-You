package com.rafael.gradle

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.compose.ComposeExtension

internal fun Project.configureComposeMultiplatform() {
    kotlinMultiplatformExtension {
        composeMultiplatformExtension {
            val compose = dependencies
            with(sourceSets) {
                commonMain.dependencies {
                    implementation(libs.findLibrary("jetbrains-compose-material3").get())
                    implementation(libs.findLibrary("jetbrains-compose-foundation").get())
                    implementation(
                        libs.findLibrary("jetbrains-compose-material-icons-extended").get(),
                    )
                    implementation(libs.findLibrary("jetbrains-compose-runtime").get())
                    implementation(libs.findLibrary("jetbrains-compose-ui").get())
                    implementation(libs.findLibrary("jetbrains-compose-animation").get())
                    implementation(libs.findLibrary("jetbrains-compose-resources").get())
                    implementation(libs.findLibrary("jetbrains-compose-ui-tooling-preview").get())
                    implementation(libs.findBundle("compose-multiplatform-ext-deps").get())
                }
                jvmMain.dependencies {
                    implementation(compose.desktop.currentOs)
                    implementation(libs.findLibrary("jetbrains-compose-desktop").get())
                }
            }
        }
    }
}
