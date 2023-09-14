// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id ("androidx.room") version "2.6.0-beta01" apply false
<<<<<<< HEAD
    id ("com.android.application") version "8.1.1" apply false
    id ("com.google.dagger.hilt.android") version "2.48" apply false
    id ("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false
=======
    id ("com.android.application") version "8.2.0-beta02" apply false
    id ("com.google.dagger.hilt.android") version "2.48" apply false
    id ("com.google.devtools.ksp") version "1.9.10-1.0.13" apply false 
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
    id ("org.jetbrains.kotlin.android") version "1.9.10" apply false
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
<<<<<<< HEAD
}
=======
}
>>>>>>> d5ae19674af2cd8a0b6887db5d50fae73d9b1ae2
