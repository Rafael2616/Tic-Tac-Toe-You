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
        versionCode = 4
        versionName = "1.3.1"
        
        buildConfigField("Boolean", "ROOM_USE_INCREMENTAL_COMPILATION", "true")
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
            versionNameSuffix = "-beta"
            resValue("string", "app_name_beta", "Jogo da Velha - Beta")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }

        named("debug") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            resValue("string", "app_name_debug", "Jogo da Velha - Debug")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = "1.5.4"
    }

    packaging {
		resources {
            excludes += listOf("/META-INF/AL2.0", "/META-INF/LGPL2.1")
        }
	}

    room {
        schemaDirectory("schemas")
    }
}

dependencies {
    val lifecycle_version = "2.6.2"
    val room_version = "2.6.0"
    val hilt_version = "2.48.1"
	val media3_version = "1.2.0-rc01"

    //Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    //Android Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation("androidx.annotation:annotation:1.7.0")
    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    ksp("com.google.dagger:hilt-android-compiler:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0")
    //Jetpack Compose
    implementation(platform("androidx.compose:compose-bom:2023.10.01"))
    implementation("androidx.compose.animation:animation:1.6.0-beta01")
    implementation("androidx.compose.foundation:foundation:1.6.0-beta01")
    implementation("androidx.compose.material:material-icons-extended:1.6.0-beta01")
    implementation("androidx.compose.material3:material3:1.2.0-alpha11")
    implementation("androidx.compose.runtime:runtime:1.6.0-beta01")
    implementation("androidx.compose.ui:ui:1.6.0-beta01")
    //Navigation
    implementation("androidx.navigation:navigation-compose:2.7.4")
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
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    //Theming
    implementation("com.google.android.material:compose-theme-adapter-3:1.1.1") 
    //Test Unit
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
}