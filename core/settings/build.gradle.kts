plugins {
    alias(libs.plugins.rafael.library)
    alias(libs.plugins.rafael.library.compose)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.ksp)
}

android.namespace = "com.rafael.tictactoe.core.settings"

room {
    schemaDirectory("$projectDir/schemas")
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            // Room
            implementation(libs.androidx.room.ktx)
            // Koin
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
        }
        commonMain.dependencies {
            // Room
            implementation(libs.androidx.room.runtime)
            // Koin
            api(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            // Projects
            api(projects.core.domain)
        }
    }
}
ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
    arg("room.incremental", "true")
    arg("room.generateKotlin", "true")
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
}
