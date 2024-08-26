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
        jvmMain.dependencies {
            // Sqlite
            implementation(libs.androidx.sqlite.bundled)
            api(libs.kotlinx.coroutines.swing)
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
    // https://github.com/google/ksp/issues/1835
    add("kspIosSimulatorArm64", libs.androidx.room.compiler)
    add("kspIosX64", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspJvm", libs.androidx.room.compiler)
}
