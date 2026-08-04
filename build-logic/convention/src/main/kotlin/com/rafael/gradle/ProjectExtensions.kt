package com.rafael.gradle

import com.android.build.api.dsl.KotlinMultiplatformAndroidLibraryTarget
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.compose.ComposeExtension
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.kotlinMultiplatformExtension(block: KotlinMultiplatformExtension.() -> Unit) {
    extensions.configure<KotlinMultiplatformExtension>(block)
}

fun Project.composeMultiplatformExtension(block: ComposeExtension.() -> Unit) {
    extensions.configure<ComposeExtension>(block)
}

fun KotlinMultiplatformExtension.android(
    configure: KotlinMultiplatformAndroidLibraryTarget.() -> Unit,
) {
    (this as ExtensionAware).extensions.configure<KotlinMultiplatformAndroidLibraryTarget>(
        "android",
        configure,
    )
}

val Project.javaVersion: JavaVersion
    get() = JavaVersion.toVersion(
        libs.findVersion("jvmTarget").get().toString(),
    )

val Project.projectNamespace: String
    get() = run {
        val namespace = libs.findVersion("namespace").get().toString()
        if (project.pluginManager.hasPlugin("com.android.application")) {
            namespace
        } else {
            "$namespace${
                project.displayName.substringAfter("'").replace(":", ".").replace("'", "").replace("-", "_")
            }"
        }
    }
