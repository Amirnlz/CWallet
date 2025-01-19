plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.hilt)
    alias(libs.plugins.devtools.ksp)
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
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }
}


dependencies {

    implementation(Dependence.AndroidX.core)
    implementation(Dependence.AndroidX.appCompat)
    implementation(Dependence.AndroidX.security)
    implementation(libs.material)

    implementation(Dependence.Web3J.crypto)
    implementation(Dependence.Web3J.core)

    implementation(Dependence.Hilt.hiltAndroid)
    ksp(Dependence.Hilt.hiltAndroidCompiler)

    testImplementation(libs.junit)
    // Optional -- Mockito framework
    testImplementation(libs.mockito.core)
    // Optional -- mockito-kotlin
    testImplementation(libs.mockito.kotlin)

    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}