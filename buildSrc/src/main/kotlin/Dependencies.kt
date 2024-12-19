import org.gradle.api.artifacts.dsl.DependencyHandler

object Dependencies {

    //    Core dependencies
    const val androidxCoreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val androidxAppcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val lifecycleRuntime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleRuntimeKtx}"

    const val material = "com.google.android.material:material:${Versions.material}"

    // Compose Dependencies
    const val composeBom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val graphicsUi = "androidx.compose.ui:ui-graphics"
    const val composeMaterial3 = "androidx.compose.material3:material3"
    const val composeUi = "androidx.compose.ui:ui"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"
    const val viewmodelCompose =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleRuntimeKtx}"
    const val uiTestmanifest = "androidx.compose.ui:ui-test-manifest:${Versions.uiTestManifest}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling"
    const val composeUiToolingPreview = "androidx.compose.ui:ui-tooling-preview"


    //    Icons
    const val iconsCore = "androidx.compose.material:material-icons-core:${Versions.iconsCore}"

    const
    val iconsExtended =
        "androidx.compose.material:material-icons-extended:${Versions.iconsExtended}"

    //    Web3
    const val web3jCrypto = "org.web3j:crypto:${Versions.crypto}"
    const val web3jCore = "org.web3j:core:${Versions.crypto}"

    //    Security
    const val securityCrypto = "androidx.security:security-crypto:${Versions.security}"


    // Hilt Dependencies
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    const val hiltNavigationCompose =
        "androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationCompose}"
    const val hiltLifecycleViewmodel =
        "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltLifecycleViemodel}"
    const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltNavigationCompose}"
    const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

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
    implementation(Dependencies.lifecycleRuntime)
    implementation(Dependencies.material)
}

fun DependencyHandler.compose() {
    val composeBom = platform(Dependencies.composeBom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeUi)
    implementation(Dependencies.graphicsUi)
    implementation(Dependencies.composeUiTooling)
    debugImplementation(Dependencies.composeUiToolingPreview)
    debugImplementation(Dependencies.uiTestmanifest)

    implementation(Dependencies.activityCompose)
    implementation(Dependencies.viewmodelCompose)
}

fun DependencyHandler.icons() {
    implementation(Dependencies.iconsCore)
    implementation(Dependencies.iconsExtended)
}

fun DependencyHandler.web3jCrypto() {
    implementation(Dependencies.web3jCore)
    implementation(Dependencies.web3jCrypto)
}

fun DependencyHandler.security() {
    implementation(Dependencies.securityCrypto)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)
    implementation(Dependencies.hiltNavigationCompose)
    implementation(Dependencies.hiltLifecycleViewmodel)
//    kapt(Dependencies.hiltCompiler)
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
