plugins {
    `android-library`
    `kotlin-android`
    //    id(Plugins.composeCompiler)

}

apply<MainGradlePlugin>()


android {
    namespace = "com.amirnlz.onboarding"
}

dependencies {
    implementation(project(":core"))
    core()
    compose()
    icons()
    hilt()
    testImplementation(libs.junit)
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}

//kapt {
//    correctErrorTypes = true
//}
