import app.cash.licensee.LicenseeTask
import app.cash.licensee.UnusedAction
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.rafael.multiplatform.library)
    alias(libs.plugins.rafael.multiplatform.library.compose)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.licensee)
    alias(libs.plugins.baselineprofile)
}

licensee {
    allow("Apache-2.0")
    allowUrl("https://opensource.org/license/mit")
    allowUrl("https://github.com/vinceglb/FileKit/blob/main/LICENSE")
    allowUrl("https://github.com/hypfvieh/dbus-java/blob/master/LICENSE")

    unusedAction(UnusedAction.LOG)
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.di)
            implementation(projects.core.platform)
            implementation(projects.core.navigationImpl)
            implementation(projects.core.resources)
        }
        jvmMain {
            val licenseeTask = tasks.named<LicenseeTask>("licenseeJvm")
            val copyArtifactsTask = tasks.register<Copy>("copyJvmArtifacts") {
                description = "The Licensee JVM copy task"
                dependsOn(licenseeTask)
                from(licenseeTask.map { it.outputDir.file("artifacts.json") })
                into(layout.buildDirectory.dir("generated/dependencyAssets/jvm"))
            }
            resources.srcDir(copyArtifactsTask.map { it.destinationDir })
        }
        wasmJsMain {
            val licenseeTask = tasks.named<LicenseeTask>("licenseeWasmJs")
            val copyArtifactsTask = tasks.register<Copy>("copyWasmJsArtifacts") {
                description = "The Licensee Wasm copy task"
                dependsOn(licenseeTask)
                from(licenseeTask.map { it.outputDir.file("artifacts.json") })
                into(layout.buildDirectory.dir("processedResources/wasmJs/main/generated/dependencyAssets/wasmJs"))
            }
            tasks.named("wasmJsProcessResources") {
                dependsOn(copyArtifactsTask)
            }
        }
    }
}

compose.desktop {
    application {
        mainClass = "com.rafael.tictactoe.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)
            packageName = "TicTacToe You"
            packageVersion = libs.versions.versionName.get()
        }
        buildTypes.release.proguard {
            obfuscate.set(false)
            configurationFiles.from(
                project.file("compose-desktop.pro"),
                project.file("proguard-rules.pro"),
            )
        }
    }
}
