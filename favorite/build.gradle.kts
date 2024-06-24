plugins {
    alias(libs.plugins.android.dynamic.feature)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("com.google.devtools.ksp")
}

apply(from="../shared_dependencies.gradle")
android {
    namespace = "com.example.favorite"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(project(":app"))
    implementation(project(":core"))
}

kotlin {
    jvmToolchain(18) // Sesuaikan dengan versi Java yang Anda inginkan
}