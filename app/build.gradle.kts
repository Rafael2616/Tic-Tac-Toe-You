import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("androidx.room")
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
}

android {
    compileSdk = 34
    namespace = "com.rafael.tictactoe"

    defaultConfig {
        applicationId = "com.rafael.tictactoe"
        minSdk = 26
        targetSdk = 34
        versionCode = 6
        versionName = "1.4.1"

        tasks.withType<KotlinCompile>().configureEach {
            ksp {
                arg("room.schemaLocation", "$projectDir/schemas")
                arg("room.incremental", "true")
                arg("room.generateKotlin", "true")
            }
        }
    }

    signingConfigs {
        create("release") {
            storeFile = file("tictactoekey.jks")
            storePassword = "rafael1800"
            keyAlias = "tictactoekey"
            keyPassword = "rafael1800"
			enableV3Signing = true
        }

        named("debug") {
            enableV3Signing = true
        }
    }

    buildTypes {
        create("beta") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            applicationIdSuffix = ".beta"
            versionNameSuffix = "-beta"
            resValue("string", "app_name_beta", "Jogo da Velha - Beta")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        named("debug") {
            signingConfig = signingConfigs.getByName("release")
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            resValue("string", "app_name_debug", "Jogo da Velha - Debug")
        }

		named("release") {
			isMinifyEnabled = true
			signingConfig = signingConfigs.getByName("release")
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
        kotlinCompilerExtensionVersion = "1.5.5"
    }

    packaging {
		resources {
            excludes += listOf("/META-INF/AL2.0", "/META-INF/LGPL2.1")
        }
	}

    room {
        schemaDirectory("$projectDir/schemas")
    }
}

dependencies {
    val lifecycle_version = "2.6.2"
    val room_version = "2.6.1"
    val hilt_version = "2.48.1"
	val media3_version = "1.2.0"

    // Todo: remove this when compose bom 2023.12.00 is released
    val m3_version = "1.6.0-beta02"

    //Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    //Android Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity-compose:1.8.1")
    implementation("androidx.annotation:annotation:1.7.0")
    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    ksp("com.google.dagger:hilt-android-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    //Jetpack Compose
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.animation:animation:$m3_version")
    implementation("androidx.compose.foundation:foundation:$m3_version")
    implementation("androidx.compose.material:material-icons-extended:$m3_version")
    implementation("androidx.compose.material3:material3:1.2.0-alpha11")
    implementation("androidx.compose.runtime:runtime:$m3_version")
    implementation("androidx.compose.ui:ui:$m3_version")
    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.5")
    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
	//Media3
    implementation("androidx.media3:media3-common:$media3_version")
	implementation("androidx.media3:media3-exoplayer:$media3_version")
    //Room
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    //Test Unit
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:$m3_version")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
}