package com.rafael.gradle

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.LibraryExtension
import org.gradle.api.Project
import org.gradle.api.file.DuplicatesStrategy
import org.gradle.jvm.tasks.Jar
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

internal fun Project.configureKotlinMultiplatform() {
    kotlinMultiplatformExtension {
        if (pluginManager.hasPlugin("com.android.kotlin.multiplatform.library")) {
            android {
                namespace = projectNamespace
                compileSdk = libs.findVersion("compileSdk").get().toString().toInt()
                minSdk = libs.findVersion("minSdk").get().toString().toInt()
            }
        } else if (pluginManager.hasPlugin("com.android.application") || pluginManager.hasPlugin("com.android.library")) {
            androidTarget()
        }

        jvm {
            @OptIn(ExperimentalKotlinGradlePluginApi::class)
            binaries {
                tasks.withType<Jar>().configureEach {
                    if (name == "${this.name}Jar") {
                        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
                        // Or exclude("META-INF/MANIFEST.MF") if needed
                    }
                }
            }
        }

        listOf(
            iosArm64(),
            // iosSimulatorArm64(), enable if you want simulator and build with Macs
        ).forEach { iosTarget ->
            val projectName = project.name.replaceFirstChar { it.uppercase() }.replace("-", "")
            iosTarget.binaries.framework {
                baseName = projectName
                isStatic = true
                freeCompilerArgs += listOf("-Xbinary=bundleId=$projectNamespace")
            }
        }
        @OptIn(ExperimentalWasmDsl::class)
        wasmJs {
            browser {
                val projectOutputFileName = "${project.name}.js"
                commonWebpackConfig {
                    outputFileName = projectOutputFileName
                }
            }
            compilations.configureEach {
                outputModuleName.apply {
                    project.provider { project.name }
                }
            }
            binaries.executable()
        }
        compilerOptions {
            // Remove when expect/actual declaration becomes stable
            freeCompilerArgs.add("-Xexpect-actual-classes")
            jvmToolchain(javaVersion.majorVersion.toInt())
        }
    }

    val compileSdkVersion = libs.findVersion("compileSdk").get().toString().toInt()
    val minSdkVersion = libs.findVersion("minSdk").get().toString().toInt()

    pluginManager.withPlugin("com.android.application") {
        extensions.configure<ApplicationExtension>("android") {
            compileSdk = compileSdkVersion
            defaultConfig {
                minSdk = minSdkVersion
            }
        }
    }
    pluginManager.withPlugin("com.android.library") {
        extensions.configure<LibraryExtension>("android") {
            compileSdk = compileSdkVersion
            defaultConfig {
                minSdk = minSdkVersion
            }
        }
    }
}
