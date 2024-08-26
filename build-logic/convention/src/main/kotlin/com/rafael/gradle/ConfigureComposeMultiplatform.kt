package com.rafael.gradle

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

@OptIn(ExperimentalComposeLibrary::class)
internal fun Project.configureComposeMultiplatform() {
    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        kotlinMultiplatformExtension {
            composeMultiplatformExtension {
                val compose = dependencies
                with(sourceSets) {
                    commonMain.dependencies {
                        implementation(compose.foundation)
                        implementation(compose.material3)
                        implementation(compose.materialIconsExtended)
                        implementation(compose.runtime)
                        implementation(compose.ui)
                        implementation(compose.animation)
                        implementation(compose.components.resources)
                        implementation(compose.components.uiToolingPreview)
                    }
                    androidMain.dependencies {
                        implementation(compose.preview)
                        implementation(compose.uiTooling)
                    }
                    androidUnitTest.dependencies {
                        implementation(compose.uiTest)
                    }
                    commonTest.dependencies {
                        implementation(compose.uiTest)
                    }
                    jvmMain.dependencies {
                        implementation(compose.desktop.currentOs)
                        implementation(compose.desktop.common)
                        implementation(compose.uiUtil)
                    }
                }
            }
        }
    }
}

fun Project.composeMultiplatformExtension(block: ComposeExtension.() -> Unit) {
    extensions.configure<ComposeExtension>(block)
}

fun Project.composeCompilerExtension(block: ComposeCompilerGradlePluginExtension.() -> Unit) {
    extensions.configure<ComposeCompilerGradlePluginExtension>(block)
}
