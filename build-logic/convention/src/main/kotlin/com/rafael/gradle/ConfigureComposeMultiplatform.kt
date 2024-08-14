package com.rafael.gradle

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.jetbrains.compose.ComposePlugin
import org.jetbrains.compose.ComposePlugin.Dependencies
import org.jetbrains.compose.ExperimentalComposeLibrary
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

@OptIn(ExperimentalComposeLibrary::class)
internal fun Project.configureComposeMultiplatform() {
    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        extensions.configure<KotlinMultiplatformExtension> {
            val compose = Dependencies(this@configureComposeMultiplatform)
            val composeCommon = ComposePlugin.CommonComponentsDependencies
            sourceSets["commonMain"].dependencies {
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.materialIconsExtended)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.animation)
                implementation(compose.components.resources)
                implementation(composeCommon.uiToolingPreview)
            }
            sourceSets["androidMain"].dependencies {
                implementation(compose.preview)
                implementation(compose.uiTooling)
            }
            sourceSets["androidUnitTest"].dependencies {
                implementation(compose.uiTest)
            }
            sourceSets["commonTest"].dependencies {
                implementation(compose.uiTest)
            }
        }
    }
}

fun Project.composeCompiler(block: ComposeCompilerGradlePluginExtension.() -> Unit) {
    extensions.configure<ComposeCompilerGradlePluginExtension>(block)
}
