plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
}

android {
    compileSdk = 33
    buildToolsVersion = "33.0.0"
    namespace = "com.rafael.tictactoe"

    defaultConfig {
        applicationId = "com.rafael.tictactoe"
        minSdk = 26
        targetSdk = 33
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs += listOf(
            "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
        )
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.7"
    }
    packagingOptions.resources.excludes += listOf(
        "/META-INF/AL2.0",
        "/META-INF/LGPL2.1"
    )
}

dependencies {

    val lifecycle_version = "2.6.1"
    val room_version = "2.6.0-alpha01"
    val hilt_version = "2.46.1"

    //Dependências Kotlin
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    
    //Dependências Room
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
    
    //Dependências Dagger Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")    
    implementation("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")
    kapt("androidx.hilt:hilt-compiler:1.0.0")

  //Dependências Lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")
    
    //Dependências Jetpack Compose
    platform("androidx.compose:compose-bom:2023.05.01")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.material3:material3:1.1.0")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.material:material-icons-core")

    //Outras dependências do Jetpack Compose
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.constraintlayout:constraintlayout-compose-android:1.1.0-alpha10")

    //Dependências para personalização do tema
    implementation("com.google.android.material:compose-theme-adapter-3:1.1.1") 
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.2-alpha")

    //Dependências de teste unitários
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Para corrigir erro relacionado ao Room na tarefa KSP
	configurations.all {
   	 resolutionStrategy {
      	  force("org.xerial:sqlite-jdbc:3.42.0.0")
   	 }
	}
}
