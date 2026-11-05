pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://maven.fabric.io/public")}
        maven { url = uri( "https://jitpack.io") }
        maven { url = uri( "https://maven.google.com") }
        maven {
            url = uri( "https://dl.bintray.com/glomadrian/maven")
            isAllowInsecureProtocol = true
        }
        gradlePluginPortal()
        /*flatDir {
            dirs("libs")
        }*/
    }
}

dependencyResolutionManagement {
    //repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri( "https://jitpack.io") }
        /*maven {
            url = uri("https://phonepe.mycloudrepo.io/public/repositories/phonepe-intentsdk-android")
            // If the repo uses HTTP or an insecure connection
            isAllowInsecureProtocol = true
        }*/
        gradlePluginPortal()
        flatDir {
            dirs("app/libs")
        }
    }
}

rootProject.name = "GithubLibrarySample"
include(":app")
//include(":app", ":razorpay-android-3.8.8")

include(":gitsample")
