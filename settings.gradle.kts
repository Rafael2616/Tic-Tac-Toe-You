import org.gradle.caching.configuration.BuildCacheConfiguration

pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google()
        mavenCentral()
    }

buildCache {
        local {
            isEnabled = true
        }
    }
}
   
rootProject.name = "TicTacToe"
include(":app")
