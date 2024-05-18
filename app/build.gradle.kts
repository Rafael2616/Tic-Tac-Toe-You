import app.cash.licensee.LicenseeTask
import app.cash.licensee.UnusedAction.IGNORE
import com.android.build.gradle.internal.api.ApkVariantOutputImpl
import java.util.Properties
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    alias(libs.plugins.rafael.gradle.android.application)
    alias(libs.plugins.rafael.gradle.android.application.compose)
    alias(libs.plugins.rafael.gradle.spotless)
    alias(libs.plugins.dagger.hilt)
    alias(libs.plugins.ksp)
    alias(libs.plugins.licensee)
}

android {
    namespace = "com.rafael.tictactoe"

    defaultConfig {
        applicationId = "com.rafael.tictactoe"
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
    // Media3
    implementation(libs.androidx.media3.exoplayer)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.mockk)

    // Projects
    implementation(projects.core.navigation)
    implementation(projects.core.navigationImpl)
    implementation(projects.core.resources)
    implementation(projects.core.services)
    implementation(projects.feature.settings)
}
