object Dependence {


    object TON {
        const val tvm = "org.ton:ton-kotlin-tvm:0.3.1"
        const val crypto = "org.ton:ton-kotlin-crypto:0.3.1"
        const val tlb = "org.ton:ton-kotlin-tlb:0.3.1"
        const val blockTlb = "org.ton:ton-kotlin-block-tlb:0.3.1"
        const val tonapiTl = "org.ton:ton-kotlin-tonapi-tl:0.3.1"
        const val contract = "org.ton:ton-kotlin-contract:0.3.1"
    }

    object KotlinX {
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.9.0"
        const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-core:1.7.3"
        const val serializationJSON = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3"
        const val serializationCBOR = "org.jetbrains.kotlinx:kotlinx-serialization-cbor:1.7.3"
        const val datetime = "org.jetbrains.kotlinx:kotlinx-datetime:0.6.1"
    }


    object Hilt {
        const val hiltAndroid = "com.google.dagger:hilt-android:2.51.1"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:2.51.1" //kapt
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:2.51.1" //kapt
        const val hiltNavigationCompose =
            "androidx.hilt:hilt-navigation-compose:1.2.0"
        const val hiltLifecycleViewmodel =
            "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
        const val hiltAgp = "com.google.dagger:hilt-android-gradle-plugin:2.51.1"
    }


    object AndroidX {
        const val core = "androidx.core:core-ktx:1.15.0"
        const val appCompat = "androidx.appcompat:appcompat:1.7.0"
        const val activity = "androidx.activity:activity-ktx:1.9.3"
        const val security = "androidx.security:security-crypto:1.0.0"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.8.7"
        const val lifecycleSavedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.7"

        object Compose {
            const val composeBom = "androidx.compose:compose-bom:2024.12.01"
            const val material3 = "androidx.compose.material3:material3"
            const val ui = "androidx.compose.ui:ui"
            const val uiGraphics = "androidx.compose.ui:ui-graphics"


            const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
            const val uiTooling = "androidx.compose.ui:ui-tooling" //debug

            const val activityCompose = "androidx.activity:activity-compose:1.9.2"
            const val lifecycleViewmodelCompose =
                "androidx.lifecycle:lifecycle-viewmodel-compose:2.8.5"

            const val materialIconsCore = "androidx.compose.material:material-icons-core"

            const
            val materialIconsExtended =
                "androidx.compose.material:material-icons-extended"
        }

        object Navigation {
            const val navigationCompose = "androidx.navigation:navigation-compose:2.8.5"

        }
    }

    object Web3J {
        const val crypto = "org.web3j:crypto:5.0.0"
        const val core = "org.web3j:core:5.0.0"
    }


    object Module {
        const val core = ":core"
        const val onboarding = ":onboarding"
        const val walletCreation = ":wallet_creation"
        const val walletRecovery = ":wallet_recovery"

        const val shimmer = ":ui:shimmer"
        const val blur = ":ui:blur"
    }

    object Lib {
        const val extensions = ":lib:extensions"
        const val network = ":lib:network"
        const val security = ":lib:security"
        const val qr = ":lib:qr"
        const val emoji = ":lib:emoji"
        const val blockchain = ":lib:blockchain"
        const val icu = ":lib:icu"
        const val sqlite = ":lib:sqlite"
        const val ur = ":lib:ur"
        const val base64 = ":lib:base64"
    }

}