// Top-level build file where you can add configuration options common to all sub-projects/modules.

import com.diffplug.gradle.spotless.SpotlessExtension

plugins {
    alias(libs.plugins.androidx.room).apply(false)
    alias(libs.plugins.com.android.application).apply(false)
    alias(libs.plugins.com.android.library).apply(false)
    alias(libs.plugins.dagger.hilt).apply(false)
    alias(libs.plugins.spotless).apply(false)
    alias(libs.plugins.ksp).apply(false)
    alias(libs.plugins.kotlin.android).apply(false)
    alias(libs.plugins.kotlin.serialization).apply(false)
    alias(libs.plugins.kotlin.parcelize).apply(false)
    alias(libs.plugins.kotlin.kapt).apply(false)
    alias(libs.plugins.jetbrains.compose).apply(false)
    alias(libs.plugins.licensee).apply(false)
}

allprojects {
    apply(plugin = rootProject.libs.plugins.spotless.get().pluginId)
    extensions.configure<SpotlessExtension> {
        kotlin {
            ktlint()
                .setEditorConfigPath(rootProject.file(".editorconfig"))
                .customRuleSets(
                    listOf(
                        libs.ktlint.compose.rules.get().toString(),
                    ),
                )
            target("src/main/kotlin/**/*.kt", "src/androidTest/java/**/*.kt")
        }
        kotlinGradle {
            ktlint()
                .setEditorConfigPath(rootProject.file(".editorconfig"))
            target("*.gradle.kts")
        }
    }
}
