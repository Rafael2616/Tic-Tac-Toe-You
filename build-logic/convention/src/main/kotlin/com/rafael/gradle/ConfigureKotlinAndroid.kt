package com.rafael.gradle

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = libs.findVersion("compileSdk").get().toString().toIntOrNull()

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
                )
            }
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
         kotlinOptions {
            jvmTarget = javaVersion.toString()
        }
    }
}

val Project.javaVersion: JavaVersion
    get() = JavaVersion.toVersion(
        libs.findVersion("javaVersion").get().toString()
    )