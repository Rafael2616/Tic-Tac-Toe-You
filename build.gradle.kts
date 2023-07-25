// Top-level build file where you can add configuration options common to all sub-projects/modules.


plugins {
    id ("com.android.tools.build:gradle:8.2.0-alpha13") apply false
    //id ("com.android.application") version "8.2.0-alpha13" apply false
    //id ("com.android.library") version "8.2.0-alpha13" apply false
    id ("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id ("com.google.devtools.ksp") version "1.9.0-1.0.12" apply false
    id ("com.google.dagger.hilt.android") version "2.46.1" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
