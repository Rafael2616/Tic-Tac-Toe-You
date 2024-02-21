import app.cash.licensee.LicenseeTask
import app.cash.licensee.UnusedAction.IGNORE
import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import com.diffplug.gradle.spotless.SpotlessExtension
import java.util.Properties
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("androidx.room")
    id("com.android.application")
    id("com.diffplug.spotless")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization")
    id("app.cash.licensee")
    id("kotlin-parcelize")
    kotlin("kapt")
}

android {
    compileSdk = 34
    namespace = "com.rafael.tictactoe"

    defaultConfig {
        applicationId = "com.rafael.tictactoe"
        minSdk = 26
        targetSdk = 34
        versionCode = 151
        versionName = "1.5.1"

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
                    "io.nlopez.compose.rules:ktlint:0.3.11",
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
    val lifecycleVersion = "2.7.0"
    val roomVersion = "2.6.1"
    val hiltVersion = "2.50"
    val media3Version = "1.2.1"
    val appSearchVersion = "1.1.0-alpha03"
    val m3Version = "1.7.0-alpha02"

    // Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")
    implementation("org.jetbrains.kotlinx:kotlinx-collections-immutable:0.3.7")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")
    // Android Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.annotation:annotation:1.7.1")
    implementation("androidx.core:core-splashscreen:1.0.1")
    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:$hiltVersion")
    ksp("com.google.dagger:hilt-android-compiler:$hiltVersion")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    // Jetpack Compose
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.animation:animation:$m3Version")
    implementation("androidx.compose.foundation:foundation:$m3Version")
    implementation("androidx.compose.material:material-icons-extended:$m3Version")
    implementation("androidx.compose.runtime:runtime:$m3Version")
    implementation("androidx.compose.ui:ui:$m3Version")
    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")
    // Coil
    implementation("io.coil-kt:coil-compose:2.5.0")
    // Material Motion
    implementation("io.github.fornewid:material-motion-compose-core:1.2.0")
    // LazyColumnScrollbar
    implementation("com.github.nanihadesuka:LazyColumnScrollbar:1.9.0")
    // Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycleVersion")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleVersion")
    // Media3
    implementation("androidx.media3:media3-common:$media3Version")
    implementation("androidx.media3:media3-exoplayer:$media3Version")
    // Room
    implementation("androidx.room:room-runtime:$roomVersion")
    implementation("androidx.room:room-ktx:$roomVersion")
    ksp("androidx.room:room-compiler:$roomVersion")
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    // App Search
    implementation("androidx.appsearch:appsearch:$appSearchVersion")
    kapt("androidx.appsearch:appsearch-compiler:$appSearchVersion")
    implementation("androidx.appsearch:appsearch-local-storage:$appSearchVersion")
    // Test Unit
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$m3Version")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
}
