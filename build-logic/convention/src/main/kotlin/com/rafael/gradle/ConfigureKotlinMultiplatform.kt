package com.rafael.gradle

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

internal fun Project.configureKotlinMultiplatform(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = libs.findVersion("compileSdk").get().toString().toIntOrNull()

        sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
        sourceSets["main"].res.srcDirs("src/androidMain/res")
        sourceSets["main"].resources.srcDirs("src/commonMain/resources")

        defaultConfig {
            minSdk = libs.findVersion("minSdk").get().toString().toIntOrNull()
        }

        compileOptions {
            sourceCompatibility = javaVersion
            targetCompatibility = javaVersion
        }

        packaging {
            resources {
                excludes += listOf(
                    "/META-INF/AL2.0",
                    "/META-INF/INDEX.LIST",
                    "/META-INF/LGPL2.1",
                    "/META-INF/LICENSE.md",
                    "/META-INF/LICENSE-notice.md",
                    "/META-INF/gradle/incremental.annotation.processors",
                )
            }
        }
    }

    plugins.withId("org.jetbrains.kotlin.multiplatform") {
        kotlinMultiplatformExtension {

            val isAndroidApp = plugins.hasPlugin("com.android.application")
            val isAndroidLibrary = plugins.hasPlugin("com.android.library")
            if (isAndroidApp || isAndroidLibrary) {

                androidTarget {
                    if (isAndroidLibrary) {
                        publishLibraryVariants("release")
                    }
                    compilerOptions {
                        jvmTarget.set(JvmTarget.JVM_17)
                    }
                }
            }

            jvm()

            listOf(
                iosX64(),
                iosArm64(),
                iosSimulatorArm64(),
            ).forEach { iosTarget ->
                iosTarget.binaries.framework {
                    baseName = project.name
                    isStatic = true
                }
            }
            @OptIn(ExperimentalWasmDsl::class)
            wasmJs {
                moduleName = project.name
                browser {
                    val projectDirPath = project.projectDir.path
                    val projectOutputFileName = "${project.name}.js"
                    commonWebpackConfig {
                        outputFileName = projectOutputFileName
                        devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                            static = (static ?: mutableListOf()).apply {
                                // Serve sources to debug inside browser
                                add(projectDirPath)
                            }
                        }
                    }
                }
                binaries.executable()
            }
            compilerOptions {
                // Remove when expect/actual declaration becomes stable
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
    }
}

fun Project.kotlinMultiplatformExtension(block: KotlinMultiplatformExtension.() -> Unit) {
    extensions.configure<KotlinMultiplatformExtension>(block)
}

val Project.javaVersion: JavaVersion
    get() = JavaVersion.toVersion(
        libs.findVersion("jvmTarget").get().toString(),
    )
