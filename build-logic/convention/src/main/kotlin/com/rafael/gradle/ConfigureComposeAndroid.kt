package com.rafael.gradle

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.compose.compiler.gradle.ComposeCompilerGradlePluginExtension

internal fun Project.configureComposeAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply { 
        buildFeatures {
            compose = true
        }

        composeCompiler {
            // https://medium.com/androiddevelopers/jetpack-compose-strong-skipping-mode-explained-cbdb2aa4b900
            enableStrongSkippingMode.set(true)
        }
    }
}


fun Project.composeCompiler(block: ComposeCompilerGradlePluginExtension.() -> Unit) {
  extensions.configure<ComposeCompilerGradlePluginExtension>(block)
}