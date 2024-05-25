package com.rafael.gradle

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
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

        dependencies {
            "implementation"(libs.findLibrary("androidx-compose-bom").get())
            "implementation"(libs.findLibrary("androidx-compose-material3").get())
            "implementation"(libs.findLibrary("androidx-compose-animation").get())
            "implementation"(libs.findLibrary("androidx-compose-ui").get())
            "implementation"(libs.findLibrary("androidx-compose-foundation").get())
            "implementation"(libs.findLibrary("androidx-compose-runtime").get())
            "implementation"(libs.findLibrary("androidx-compose-material-icons-extended").get())
            "implementation"(libs.findLibrary("androidx-lifecycle-runtime-compose").get())
            "implementation"(libs.findLibrary("androidx-lifecycle-viewmodel-compose").get())
            "debugImplementation"(libs.findLibrary("androidx-compose-ui-test-manifest").get())
            "androidTestImplementation"(libs.findLibrary("androidx-compose-ui-test-junit4").get())
        }
    }
}


fun Project.composeCompiler(block: ComposeCompilerGradlePluginExtension.() -> Unit) {
  extensions.configure<ComposeCompilerGradlePluginExtension>(block)
}
