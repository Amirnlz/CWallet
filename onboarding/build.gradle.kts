plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.amirnlz.onboarding"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
}

dependencies {

    implementation(Dependence.AndroidX.core)
    implementation(Dependence.AndroidX.appCompat)

    implementation(platform(Dependence.AndroidX.Compose.composeBom))
    implementation(Dependence.AndroidX.Compose.ui)
    implementation(Dependence.AndroidX.Compose.uiGraphics)
    implementation(Dependence.AndroidX.Compose.uiToolingPreview)
    implementation(Dependence.AndroidX.Compose.material3)
    implementation(Dependence.AndroidX.Compose.lifecycleViewmodelCompose)
    implementation(Dependence.AndroidX.Compose.materialIconsCore)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}