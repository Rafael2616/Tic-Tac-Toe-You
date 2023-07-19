plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    compileSdk = 34
    buildToolsVersion = "33.0.2"
    namespace = "com.rafael.tictactoe"

    defaultConfig {
        applicationId = "com.rafael.tictactoe"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "1.1"
    }
    signingConfigs {
        create("release") {
            storeFile = file("tictactoekey.jks")
            storePassword = "rafael1800"
            keyAlias = "tictactoekey"
            keyPassword = "rafael1800"
        }
    }
    buildTypes {
        named("release") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        create("beta") {
            isMinifyEnabled = true
            signingConfig = signingConfigs.getByName("release")
            versionNameSuffix = "-beta"
            resValue("string", "app_name_beta", "Jogo da Velha - Beta")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        named("debug") {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
            resValue("string", "app_name_debug", "Jogo da Velha - Debug")
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    /*kotlin {
        sourceSets.all {
            languageSettings {
                languageVersion = "2.0"
            }
        }
    }*/
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_20
        targetCompatibility = JavaVersion.VERSION_20
    }
    kotlinOptions {
        jvmTarget = "20"
        freeCompilerArgs += listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.0"
    }
    packagingOptions.resources.excludes += listOf(
        "/META-INF/AL2.0",
        "/META-INF/LGPL2.1"
    )
}

dependencies {

    val lifecycle_version = "2.6.1"
    val room_version = "2.5.2"
    val hilt_version = "2.46.1"

    //Kotlin
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.2")
    
    //Room
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    
    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")
    
    //Jetpack Compose
    platform("androidx.compose:compose-bom:2023.06.01")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3:1.1.1")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.material:material-icons-core")

    //Others Jetpack deps
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha10")

    //Theming
    implementation("com.google.android.material:compose-theme-adapter-3:1.1.1") 
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.5-beta")

    //Test Unit
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //To fix Room issue in KSP task
	configurations.all {
   	 resolutionStrategy {
      	  force("org.xerial:sqlite-jdbc:3.42.0.0")
   	 }
	}
}
