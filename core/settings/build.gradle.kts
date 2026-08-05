plugins {
    alias(libs.plugins.rafael.multiplatform.library)
    alias(libs.plugins.rafael.multiplatform.library.compose)
    alias(libs.plugins.rafael.spotless)
    alias(libs.plugins.androidx.room)
    alias(libs.plugins.ksp)
}

kotlin {
    sourceSets {
        androidMain.dependencies {
            implementation(libs.koin.android)
            implementation(libs.koin.androidx.compose)
            implementation(libs.androidx.sqlite.bundled)
        }
        commonMain.dependencies {
            api(libs.koin.core)
            implementation(libs.koin.core.viewmodel)
            implementation(libs.kotlinx.coroutines)
            implementation(libs.androidx.room.runtime)
            api(projects.core.domain)
        }
        jvmMain.dependencies {
            api(libs.kotlinx.coroutines.swing)
            implementation(libs.androidx.sqlite.bundled)
        }
        iosMain.dependencies {
            implementation(libs.androidx.sqlite.bundled)
        }
        wasmJsMain.dependencies {
            implementation(libs.androidx.sqlite.web)
            implementation(npm("@sqlite.org/sqlite-wasm", "3.53.0-build1"))
        }
    }
}

room3 {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    add("kspAndroid", libs.androidx.room.compiler)
    add("kspIosArm64", libs.androidx.room.compiler)
    add("kspJvm", libs.androidx.room.compiler)
    add("kspWasmJs", libs.androidx.room.compiler)
}
