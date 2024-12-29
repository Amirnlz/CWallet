plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
}

android {
    namespace = "com.amirnlz.cwallet"
    compileSdk = Build.compileSdkVersion

    defaultConfig {
        applicationId = "com.amirnlz.cwallet"
        minSdk = Build.minSdkVersion
        targetSdk = Build.targetSdkVersion
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(Dependence.AndroidX.core)
    implementation(Dependence.AndroidX.lifecycle)
    implementation(Dependence.AndroidX.Compose.activityCompose)
    implementation(platform(Dependence.AndroidX.Compose.composeBom))
    implementation(Dependence.AndroidX.Compose.ui)
    implementation(Dependence.AndroidX.Compose.uiGraphics)
    implementation(Dependence.AndroidX.Compose.uiToolingPreview)
    implementation(Dependence.AndroidX.Compose.material3)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(Dependence.AndroidX.Compose.composeBom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    debugImplementation(Dependence.AndroidX.Compose.uiTooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}