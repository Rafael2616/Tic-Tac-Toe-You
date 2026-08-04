import app.cash.licensee.LicenseeTask
import app.cash.licensee.UnusedAction
import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.rafael.android.compose)
    alias(libs.plugins.licensee)
//    alias(libs.plugins.baselineprofile)
}

android {
    namespace = "com.rafael.tictactoe"

    defaultConfig {
        applicationId = "com.rafael.tictactoe"
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()
        compileSdk = libs.versions.compileSdk.get().toInt()
        minSdk = libs.versions.minSdk.get().toInt()
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
        named("debug") {
            signingConfig = releaseSigningConfig
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            resValue("string", "app_name_debug", "Jogo da Velha - Debug")
        }

        named("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = releaseSigningConfig
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    buildFeatures {
        buildConfig = true
        resValues = true
    }

    androidComponents.onVariants { variant ->
        val variantNameCapt = variant.name.replaceFirstChar { it.uppercase() }
        val licenseeTask = tasks.named<LicenseeTask>("licenseeAndroid$variantNameCapt")
        val copyArtifactsTask = tasks.register<Copy>("copy${variantNameCapt}Artifacts") {
            description = "The Licensee Android copy task"
            dependsOn(licenseeTask)
            from(licenseeTask.map { it.outputDir.file("artifacts.json") })
            into(layout.buildDirectory.dir("generated/dependencyAssets/${variant.name}"))
        }
        variant.sources.assets?.addGeneratedSourceDirectory(licenseeTask) {
            objects.directoryProperty().fileProvider(copyArtifactsTask.map { it.destinationDir })
        }
    }

    base.archivesName = "TicTacToe-You v${libs.versions.versionName.get()}"
}

licensee {
    allow("Apache-2.0")
    allowUrl("https://opensource.org/license/mit")
    allowUrl("https://github.com/vinceglb/FileKit/blob/main/LICENSE")

    unusedAction(UnusedAction.LOG)
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.androidx.annotation)
    implementation(projects.core.di)
    implementation(projects.core.platform)
    implementation(projects.core.navigationImpl)
    implementation(projects.core.resources)
//    baselineProfile(projects.baselineProfile)
}

//baselineProfile {
//    // Don't build on every iteration of a full assemble.
//    // Instead, enable generation directly for the release build variant.
//    automaticGenerationDuringBuild = false
//    warnings {
//        maxAgpVersion = false
//    }
//}
