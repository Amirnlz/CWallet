plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlinAndroidKsp) apply false
    alias(libs.plugins.hiltAndroid) apply false
}


tasks.register<Delete>("clean") {
    delete(layout.buildDirectory)
}