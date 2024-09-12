import app.cash.licensee.LicenseeTask
import app.cash.licensee.UnusedAction.IGNORE
import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import java.util.Properties
import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.rafael.application)
    alias(libs.plugins.rafael.application.compose)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.licensee)
    alias(libs.plugins.baselineprofile)
}

android {
    namespace = "com.rafael.tictactoe"

    defaultConfig {
        applicationId = "com.rafael.tictactoe"
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    val releaseSigningFile = rootProject.file("keystore.properties")
    val keystoreProperties = Properties()
    val releaseSigningConfig = if (releaseSigningFile.exists()) {
        keystoreProperties.load(releaseSigningFile.inputStream())
        signingConfigs.create("release") {
            storeFile = file(keystoreProperties.getProperty("KEYSTORE"))
            storePassword = keystoreProperties.getProperty("KEYSTORE_PASSWORD")
            keyAlias = keystoreProperties.getProperty("KEY_ALIAS")
            keyPassword = keystoreProperties.getProperty("KEY_PASSWORD")
            enableV3Signing = true
        }
    } else {
        signingConfigs.create("release") {
            storeFile = file("tictactoekey.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD")
            keyAlias = System.getenv("KEY_ALIAS")
            keyPassword = System.getenv("KEY_PASSWORD")
            enableV3Signing = true
        }
    }

    buildTypes {
        create("beta") {
            isMinifyEnabled = true
            signingConfig = releaseSigningConfig
            applicationIdSuffix = ".beta"
            versionNameSuffix = "-beta"
            resValue("string", "app_name_beta", "Jogo da Velha - Beta")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }

        named("debug") {
            signingConfig = releaseSigningConfig
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            resValue("string", "app_name_debug", "Jogo da Velha - Debug")
        }

        named("release") {
            isMinifyEnabled = true
            signingConfig = releaseSigningConfig
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }

    androidComponents.onVariants { variant ->
        val variantNameCapt = variant.name.replaceFirstChar { it.uppercase() }
        val licenseeTask = tasks.named<LicenseeTask>("licenseeAndroid$variantNameCapt")
        val copyArtifactsTask = tasks.register<Copy>("copy${variantNameCapt}Artifacts") {
            dependsOn(licenseeTask)
            from(licenseeTask.map { it.outputDir.file("artifacts.json") })
            into(layout.buildDirectory.dir("generated/dependencyAssets/${variant.name}"))
        }
        variant.sources.assets?.addGeneratedSourceDirectory(licenseeTask) {
            objects.directoryProperty().fileProvider(copyArtifactsTask.map { it.destinationDir })
        }
    }

    applicationVariants.all {
        outputs.all {
            (this as? ApkVariantOutputImpl)?.outputFileName =
                "TicTacToe-You v$versionName.apk"
        }
    }
}

licensee {
    allow("Apache-2.0")
    allow("EPL-1.0")
    allow("LGPL-2.1-only")
    allow("MIT")
    allow("BSD-3-Clause")
    allowUrl("https://www.gnu.org/licenses/gpl-3.0.en.html")
    allowUrl("http://openjdk.java.net/legal/gplv2+ce.html")
    allowUrl("https://glassfish.dev.java.net/nonav/public/CDDL+GPL.html")
    allowUrl("https://github.com/icerockdev/moko-permissions/blob/master/LICENSE.md")
    allowUrl("https://github.com/vinceglb/FileKit/blob/main/LICENSE")
    allowUrl("https://github.com/hypfvieh/dbus-java/blob/master/LICENSE")

    unusedAction(IGNORE)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            // AndroidX
            implementation(libs.androidx.core)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.core.splashscreen)
            implementation(libs.androidx.profileinstaller)
            implementation(libs.androidx.annotation)
        }
        androidUnitTest.dependencies {
            implementation(libs.junit)
        }
        androidInstrumentedTest.dependencies {
            implementation(libs.androidx.test.espresso.core)
            implementation(libs.androidx.test.ext.junit)
            implementation(libs.mockk)
        }
        commonMain.dependencies {
            // Projects
            implementation(projects.core.di)
            implementation(projects.core.platform)
            implementation(projects.core.navigationImpl)
            implementation(projects.core.resources)
        }
        jvmMain {
            val licenseeTask = tasks.named<LicenseeTask>("licenseeJvm")
            val copyArtifactsTask = tasks.register<Copy>("copyJvmArtifacts") {
                dependsOn(licenseeTask)
                from(licenseeTask.map { it.outputDir.file("artifacts.json") })
                into(layout.buildDirectory.dir("generated/dependencyAssets/jvm"))
            }
            resources.srcDir(copyArtifactsTask.map { it.destinationDir })
        }
        wasmJsMain {
            val licenseeTask = tasks.named<LicenseeTask>("licenseeWasmJs")
            val copyArtifactsTask = tasks.register<Copy>("copyWasmJsArtifacts") {
                dependsOn(licenseeTask)
                from(licenseeTask.map { it.outputDir.file("artifacts.json") })
                into(layout.buildDirectory.dir("processedResources/wasmJs/main/generated/dependencyAssets/wasmJs"))
            }
            resources.srcDir(copyArtifactsTask.map { it.destinationDir })

            tasks.named("wasmJsProcessResources") {
                dependsOn(copyArtifactsTask)
            }
        }
    }
}

dependencies {
    baselineProfile(projects.baselineProfile)
}

baselineProfile {
    // Don't build on every iteration of a full assemble.
    // Instead enable generation directly for the release build variant.
    automaticGenerationDuringBuild = false
}

compose.desktop {
    application {
        mainClass = "MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb, TargetFormat.Exe)
            packageName = "TicTacToe You"
            packageVersion = libs.versions.versionName.get()
        }
        buildTypes.release.proguard {
            obfuscate.set(true)
            configurationFiles.from(
                project.file("compose-desktop.pro"),
                project.file("proguard-rules.pro"),
            )
        }
    }
}
