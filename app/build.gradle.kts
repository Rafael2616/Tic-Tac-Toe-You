plugins {
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
}

android {
    compileSdk = 34
    namespace = "com.rafael.tictactoe"

    defaultConfig {
        applicationId = "com.rafael.tictactoe"
        minSdk = 26
        targetSdk = 34
        versionCode = 3
        versionName = "1.2"
    }
	
    signingConfigs {
        create("release") {
            storeFile = file("tictactoekey.jks")
            storePassword = "rafael1800"
            keyAlias = "tictactoekey"
            keyPassword = "rafael1800"
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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
	
    kotlin {
        jvmToolchain {
			languageVersion = JavaLanguageVersion.of(17)
		}
    }
	
    buildFeatures {
        compose = true
    }
	
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
	
    packaging {
		resources.excludes += listOf("/META-INF/AL2.0", "/META-INF/LGPL2.1")
	}
}

dependencies {

    val lifecycle_version = "2.6.1"
    val room_version = "2.5.2"
    val hilt_version = "2.47"
	val media3_version = "1.1.0"
    
    //Kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //Android Core
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.activity:activity-compose:1.7.2")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    //Jetpack Compose
    platform("androidx.compose:compose-bom:2023.06.01")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material-icons-core")
    implementation("androidx.compose.material3:material3:1.1.1")
    implementation("androidx.compose.runtime:runtime-livedata")
    implementation("androidx.compose.ui:ui")

    //Jetpack ConstraintLayout
    implementation("androidx.constraintlayout:constraintlayout-compose:1.1.0-alpha11")

    //Lifecycle
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:$lifecycle_version")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version")

	//Media3
    implementation("androidx.media3:media3-common:$media3_version")
	implementation("androidx.media3:media3-exoplayer:$media3_version")

    //Room
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    ksp("androidx.room:room-compiler:$room_version")

    //Theming
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.31.6-rc")
    implementation("com.google.android.material:compose-theme-adapter-3:1.1.1") 

    //Test Unit
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.4.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")

    //Fix Room issue in KSP task
	configurations.all {
		resolutionStrategy {
			force("org.xerial:sqlite-jdbc:3.42.0.0")
		}
	}
}