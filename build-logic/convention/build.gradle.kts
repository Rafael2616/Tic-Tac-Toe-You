import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.rafael.gradle"

// Configure the build-logic plugins to target JDK 17
// This matches the JDK used to build the project, and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradle)
    compileOnly(libs.kotlin.gradle)
    compileOnly(libs.spotless.gradle)
}

gradlePlugin {
    // register the convention plugin
    plugins {
        register("spotlessLibrary") {
            id = "com.rafael.gradle.spotless"
            implementationClass = "AndroidSpotlessConventionPlugin"
        }
        register("androidLibrary") {
            id = "com.rafael.gradle.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
    }
}
