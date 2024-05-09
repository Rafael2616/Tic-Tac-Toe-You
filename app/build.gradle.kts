import app.cash.licensee.LicenseeTask
import app.cash.licensee.UnusedAction.IGNORE
import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import com.diffplug.gradle.spotless.SpotlessExtension
import java.util.Properties
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.com.android.application)
    alias(libs.plugins.spotless)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.jetbrains.compose)
    alias(libs.plugins.licensee)
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

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

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
            languageVersion = JavaLanguageVersion.of(libs.versions.javaVersion.get().toInt())
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
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

composeCompiler {
    // https://medium.com/androiddevelopers/jetpack-compose-strong-skipping-mode-explained-cbdb2aa4b900
    enableStrongSkippingMode = true
}

configure<SpotlessExtension> {
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

licensee {
    allow("Apache-2.0")
    allow("EPL-1.0")
    allow("LGPL-2.1-only")
    allow("MIT")
    allowUrl("https://www.gnu.org/licenses/gpl-3.0.en.html")
    allowDependency("com.github.SmartToolFactory", "Compose-Extended-Colors", libs.versions.smarttoolfactory.extended.colors.get()) {
        because("No license provided for this dependency")
    }
    unusedAction(IGNORE)
}

dependencies {
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
    // Media3
    implementation(libs.androidx.media3.exoplayer)
    // Tests
    debugImplementation(libs.androidx.compose.ui.test.manifest)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.mockk)

    // Projects
    implementation(project(":core:navigation-impl"))
    implementation(project(":core:navigation"))
    implementation(project(":core:services"))
    implementation(project(":feature:settings"))
    implementation(project(":core:resources"))
}
