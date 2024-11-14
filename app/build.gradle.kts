plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.example.librarymanagementsystem"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.librarymanagementsystem"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
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
    }
}

dependencies {
    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // Activity and Fragment dependencies
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.fragment.ktx)

    // Kotlin and Coroutines dependencies
    implementation(platform(libs.kotlin.bom))
    implementation(libs.kotlin.reflect)
    implementation(libs.coroutines.core)
    implementation(libs.coroutines.android)

    // Lifecycle and ViewModel dependencies
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)

    // Image loading with Glide
    implementation(libs.glide)
    annotationProcessor(libs.glide.annotations)

    // Dependency Injection with Hilt
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.compiler)

    // Database dependencies (Room)
    implementation(libs.android.room)
    implementation(libs.android.room.ktx)
    ksp(libs.android.room.compiler)

    // Unit Testing dependencies
    testImplementation(libs.junit)
    testImplementation(libs.android.room.testing)
    testImplementation(libs.mockk.mockk)
    testImplementation(libs.kotlinx.coroutines.test)

    // Instrumented Testing dependencies
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

