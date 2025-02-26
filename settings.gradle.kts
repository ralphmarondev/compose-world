pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Compose World"
include(":app")
include(":features:home")
include(":features:browser")
include(":features:calculator")
include(":features:notes")
include(":features:settings")
include(":features:keepr")
include(":features:onboarding")
include(":core:data")
include(":features:clock")
include(":features:weather")
