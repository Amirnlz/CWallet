import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    //    Core dependencies
    const val androidxCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val androidxAppcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"

    // Compose Dependencies
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    const val composeUi = "androidx.compose.ui:ui"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val viewmodelCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewmodelCompose}"

    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"


    //    Icons
    const val iconsCore = "androidx.compose.material:material-icons-core:${Versions.iconsCore}"

    const
    val iconsExtended =
        "androidx.compose.material:material-icons-extended:${Versions.iconsExtended}"


    // Hilt Dependencies
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"

    // Retrofit Dependencies
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val moshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"

    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okHttp}"
    const val okhttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"

    // Room Dependencies
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
}

fun DependencyHandler.core() {
    implementation(Dependencies.androidxCoreKtx)
    implementation(Dependencies.androidxAppcompat)
    implementation(Dependencies.material)
}

fun DependencyHandler.compose() {
    val composeBom = platform(Dependencies.composeBom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.composeUiTooling)
    debugImplementation(Dependencies.composeUiToolingPreview)

    implementation(Dependencies.activityCompose)
    implementation(Dependencies.viewmodelCompose)
}

fun DependencyHandler.icons() {
    implementation(Dependencies.iconsCore)
    implementation(Dependencies.iconsExtended)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltCompiler)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.retrofit)
    implementation(Dependencies.moshiConverter)
    implementation(Dependencies.okhttp)
    implementation(Dependencies.okhttpLoggingInterceptor)
}

fun DependencyHandler.room() {
    implementation(Dependencies.roomRuntime)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomCompiler)
}


