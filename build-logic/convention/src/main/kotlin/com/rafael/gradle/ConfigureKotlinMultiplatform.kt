package com.rafael.gradle

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.dsl.KotlinJsCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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
                    @OptIn(ExperimentalKotlinGradlePluginApi::class)
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
                    baseName = this@configureKotlinMultiplatform.name
                    isStatic = true
                }
            }
            @OptIn(ExperimentalWasmDsl::class)
            wasmJs {
                moduleName = project.name
                browser()
//                https://youtrack.jetbrains.com/issue/KT-68614
//                browser {
//                    val projectDirPath = project.projectDir.path
//                    commonWebpackConfig {
//                        outputFileName = "${project.name}.js"
//                        devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
//                            static = (static ?: mutableListOf()).apply {
//                                // Serve sources to debug inside browser
//                                add(projectDirPath)
//                            }
//                        }
//                    }
//                }
                binaries.executable()
            }
        }
        tasks.withType<KotlinCompile>().configureEach {
            // Remove when expect/actual declaration becomes stable
            compilerOptions {
                freeCompilerArgs.add("-Xexpect-actual-classes")
            }
        }
        tasks.withType<KotlinJsCompile>().configureEach {
            // Remove when expect/actual declaration becomes stable
            compilerOptions {
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
