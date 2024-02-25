import app.cash.licensee.LicenseeTask
import app.cash.licensee.UnusedAction.IGNORE
import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import com.diffplug.gradle.spotless.SpotlessExtension
import java.util.Properties
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.spotless)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.licensee)
    kotlin("kapt")
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "com.rafael.tictactoe"

    defaultConfig {
        applicationId = "com.rafael.tictactoe"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.versionCode.get().toInt()
        versionName = libs.versions.versionName.get()

        tasks.withType<KotlinCompile>().configureEach {
            ksp {
                arg("room.schemaLocation", "$projectDir/schemas")
                arg("room.incremental", "true")
                arg("room.generateKotlin", "true")
            }
        }
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kotlin {
        jvmToolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.10"
    }

    packaging {
        resources {
            excludes += listOf("/META-INF/AL2.0", "/META-INF/LGPL2.1")
        }
    }

    room {
        schemaDirectory("$projectDir/schemas")
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

configure<SpotlessExtension> {
    kotlin {
        ktlint()
            .setEditorConfigPath(rootProject.file(".editorconfig"))
            .customRuleSets(
                listOf(
                    "io.nlopez.compose.rules:ktlint:${libs.versions.compose.rules.get()}",
                ),
            )
        target("src/main/kotlin/**/*.kt")
    }
    kotlinGradle {
        ktlint()
            .setEditorConfigPath(rootProject.file(".editorconfig"))
        target("*.gradle.kts")
    }
}

licensee {
    allow("Apache-2.0")
    allow("MIT")
    allowUrl("https://www.gnu.org/licenses/gpl-3.0.en.html")
    unusedAction(IGNORE)
}

dependencies {
    // Kotlin
    implementation(libs.kotlinx.coroutines)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)
    // Android Core
    implementation(libs.androidx.core)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.annotation)
    implementation(libs.androidx.core.splashscreen)
    // Dagger Hilt
    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.hilt.navigation.compose)
    ksp(libs.dagger.hilt.android.compiler)
    // Jetpack Compose
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material.icons.extended)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.ui)
    // Navigation
    implementation(libs.androidx.navigation.compose)
    // Coil
    implementation(libs.coil.compose)
    // Material Motion
    implementation(libs.material.motion.compose)
    // LazyColumnScrollbar
    implementation(libs.nanihadesuka.lazyColumnScrollbar)
    // Lifecycle
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // Media3
    implementation(libs.androidx.media3.common)
    implementation(libs.androidx.media3.exoplayer)
    // Room
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    // Retrofit
    implementation(libs.retrofit2)
    implementation(libs.retrofit2.converter.gson)
    implementation(libs.okhttp3)
    // App Search
    implementation(libs.androidx.appsearch)
    implementation(libs.androidx.appsearch.localStorage)
    kapt(libs.androidx.appsearch.compiler)
    // Test Unit
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
}
