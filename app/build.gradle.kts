import java.util.regex.Pattern.compile

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id ("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.example.myapplication"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myapplication"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            applicationIdSuffix = ".release"
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            applicationIdSuffix = ".debug"
        }
    }

    flavorDimensions += listOf("mode")

    productFlavors {
        create("dev") {
            // Assigns this product flavor to the "mode" flavor dimension.
            dimension = "mode"
            buildConfigField("String", "BASE_URL","\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "BASE_URL_IMAGE","\"https://image.tmdb.org/t/p/w500\"")

        }

        create("stg") {
            dimension = "mode"
            buildConfigField("String", "BASE_URL","\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "BASE_URL_IMAGE","\"https://image.tmdb.org/t/p/w500\"")
        }

        create("prod") {
            dimension = "mode"
            buildConfigField("String", "BASE_URL","\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "BASE_URL_IMAGE","\"https://image.tmdb.org/t/p/w500\"")
        }

//        create("minApi24") {
//            dimension = "api"
//            minSdk = 24
//            // To ensure the target device receives the version of the app with
//            // the highest compatible API level, assign version codes in increasing
//            // value with API level.
//            versionCode = 30000 + (android.defaultConfig.versionCode ?: 0)
//            versionNameSuffix = "-minApi24"
//        }
//
//        create("minApi23") {
//            dimension = "api"
//            minSdk = 23
//            versionCode = 20000  + (android.defaultConfig.versionCode ?: 0)
//            versionNameSuffix = "-minApi23"
//        }
    }


    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")

    //Gson
    implementation("com.google.code.gson:gson:2.11.0")

    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    //OkHttp3
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
    implementation("com.github.franmontiel:PersistentCookieJar:v1.0.1")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    //Paging
    implementation("androidx.paging:paging-runtime-ktx:3.3.0")


    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}