plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    kotlin("kapt")
}

android {
    namespace = "com.amirnlz.core"
    compileSdk = Build.compileSdkVersion

    defaultConfig {
        minSdk = Build.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    implementation(Dependence.AndroidX.core)
    implementation(Dependence.AndroidX.appCompat)
    implementation(Dependence.AndroidX.security)
    implementation(libs.material)

    implementation(Dependence.Web3J.crypto)
    implementation(Dependence.Web3J.core)

    implementation(Dependence.Hilt.hiltAndroid)
    implementation(Dependence.Hilt.hiltLifecycleViewmodel)
    kapt(Dependence.Hilt.hiltCompiler)


    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}