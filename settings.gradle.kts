pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven ("https://jitpack.io")
    }
}

dependencyResolutionManagement {
    repositoriesMode = RepositoriesMode.FAIL_ON_PROJECT_REPOS
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        maven("https://jitpack.io")
    }	
    buildCache {
        local {
            isEnabled = true
        }
    }
}

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "TicTacToe-You"

include(":app")
include(":core:domain")
include(":core:navigation")
include(":core:navigation-impl")
include(":core:resources")
include(":core:services")
include(":core:ui")
include(":feature:changelogs")
include(":feature:game")
include(":feature:library-info")
include(":feature:main")
include(":feature:settings")
include(":feature:settings-search")
