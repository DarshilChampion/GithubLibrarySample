import org.jetbrains.kotlin.gradle.utils.IMPLEMENTATION
//import org.jetbrains.kotlin.ir.backend.js.compile
import java.text.SimpleDateFormat
import java.util.Date

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.ksp)
    /*alias(libs.plugins.googleServices)
    alias(libs.plugins.crashlytics)
    alias(libs.plugins.firebasePerf)*/
}

android {
    namespace = "com.darshilchampion.samplelibrary"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.darshilchampion.samplelibrary"
        minSdk = 27
        targetSdk = 36
        versionCode = 1
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    buildFeatures{
        buildConfig = true
        dataBinding = true
        viewBinding = true
    }

    packaging {
        resources.excludes += setOf(
            "META-INF/LICENSE",
            "META-INF/ASL2.0",
            "META-INF/rxjava.properties",
            "VERSION.txt"
        )
    }
    lint {
        checkReleaseBuilds = false
        abortOnError = false
    }

    /*repositories {
        flatDir {
            dirs("libs")
        }
    }*/


    configurations.all {
        exclude(group = "com.google.android", module = "support-v4")
    }


}
/*repositories {
    google()
    mavenCentral()
    flatDir {
        dirs("app/libs")
    }
}*/

dependencies {

    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.constraintlayout)
//    implementation(libs.androidx.recyclerview)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // ViewModel & LiveData
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    /*implementation(fileTree(mapOf(
        "dir" to "D:\\DarshilChampionProject\\MyApplicationNew\\app\\libs",
        "include" to listOf("*.aar", "*.jar"),
//        "exclude" to listOf("D:\\DarshilChampionProject\\MyApplicationNew\\app\\libs\\razorpay-android-3.8.8.aar")
    )))*/

    //implementation(":finosdk@aar")
    /*implementation(":finosdk-release@aar")
    implementation(":tez-client-api-0.9.4@aar")
    implementation(":PROD-partnerSDK-release@aar")*/


    testImplementation(libs.junit)
    androidTestImplementation (libs.androidx.test.runner)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


    // Core libraries
    implementation(libs.material)
    implementation(libs.multidex)
    implementation(libs.gson)
    implementation(libs.ion)

    // Google Play Services
    implementation(libs.play.services.maps)
    implementation(libs.play.services.location)
    implementation(libs.play.services.tasks)
    implementation(libs.play.services.auth)
    implementation(libs.play.services.auth.api.phone)
    implementation(libs.androidx.core.splashscreen)

    // Firebase
    /*implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics.ktx)
    implementation(libs.firebase.crashlytics.ktx)
    implementation(libs.firebase.messaging.ktx)
    implementation(libs.firebase.perf.ktx)
    implementation(libs.firebase.config.ktx)
    implementation(libs.firebase.database.ktx)*/
    //implementation(libs.firebase.auth.ktx)

    // Additional libraries
    implementation(libs.picasso)
    implementation(libs.androidx.security.crypto)
    implementation (libs.viewpager.dots.indicator)

    implementation (libs.rxandroid)
    implementation (libs.rxjava)
    implementation (libs.zoomableimageview)

    implementation(libs.app.update)
    implementation(libs.app.update.ktx)

    implementation (libs.androidsvg.aar)
    implementation (libs.vector.child.finder)
    implementation (libs.ucrop)

    implementation(libs.android.image.cropper)
    //scn QR code
    implementation (libs.zxing.android.embedded)
    implementation (libs.zxing)
    implementation (libs.zxing.core)

    implementation (libs.viewpager.dots.indicator)//Introscreen Indicator
    implementation (libs.rootbeer.lib) //Root Checker



    implementation (libs.retrofit)
    implementation (libs.retrofit.converter.gson)
    implementation (libs.okhttp.logging)
    implementation (libs.okhttp)
    implementation (libs.circle.image.view) //Circle Image View
    implementation (libs.roundedimageview) //Round Corner Image View
    implementation (libs.shimmer)

    implementation (libs.lottie)


    implementation(libs.room.runtime)
    implementation(libs.room.ktx)

    ksp(libs.room.compiler)
    //implementation(libs.android.pdf.viewer)

    //implementation (libs.mpandroidchart)

    /*implementation(libs.katexview)
    implementation(libs.jlatexmath.android)
    // for Cyrillic symbols
    implementation(libs.jlatexmath.android.font.cyrillic)
    // for Greek symbols
    implementation(libs.jlatexmath.android.font.greek)*/


    //implementation(libs.androidx.biometric)

    //implementation(libs.razorpaycheckout)
    //implementation("com.razorpay:checkout:1.6.41")
    //implementation "com.razorpay:razorpay-java:1.4.4"

    //implementation(libs.androidx.swiperefreshlayout)

}