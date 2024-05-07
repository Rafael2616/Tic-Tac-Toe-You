import org.gradle.caching.configuration.BuildCacheConfiguration

pluginManagement {
	repositories {
   	 gradlePluginPortal()
  	  google()
  	  mavenCentral()
        maven { setUrl("https://jitpack.io") }
	}
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google()
        mavenCentral()
        maven { setUrl("https://jitpack.io") }
    }	
	buildCache {
		local {
            isEnabled = true
        }
    }
}

rootProject.name = "TicTacToe You"
include(":app")
include(":feature:settings-search")
include(":feature:settings")
include(":feature:main")
include(":feature:game")
include(":feature:library-info")
include(":feature:changelogs")
include(":core:services")
include(":core:domain")
include(":core:ui")
include(":core:navigation")
include(":core:navigation-wrapper")
include(":core:di")
include(":core:resources")
