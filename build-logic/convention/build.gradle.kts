import com.diffplug.gradle.spotless.SpotlessExtension
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    `kotlin-dsl`
     alias(libs.plugins.spotless)
}

group = "com.rafael.gradle"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinJvmCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_17)
    }
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.spotless.gradle)
    compileOnly(libs.composeCompiler.gradle)
}

gradlePlugin {
    // register the convention plugin
    plugins {
        register("spotlessLibrary") {
            id = "com.rafael.gradle.spotless"
            implementationClass = "AndroidSpotlessConventionPlugin"
        }
        register("androidLibrary") {
            id = "com.rafael.gradle.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidApplication") {
            id = "com.rafael.gradle.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("composeLibrary") {
            id = "com.rafael.gradle.compose.library"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("composeApplication") {
            id = "com.rafael.gradle.compose.application"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
    }
}

extensions.configure<SpotlessExtension> {
    kotlin {
        ktlint()
            .setEditorConfigPath(rootProject.file(".editorconfig"))
            .customRuleSets(
                listOf(
                    libs.ktlint.compose.rules.get().toString(),
                ),
            )
        target("src/**/*.kt")
    }
    kotlinGradle {
        ktlint()
            .setEditorConfigPath(rootProject.file(".editorconfig"))
        target("*.gradle.kts")
    }
}
