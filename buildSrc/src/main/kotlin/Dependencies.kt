object Kotlin {
    const val stdLib = "1.5.10"
}

object AndroidSdk {
    const val min = 23
    const val compile = 30
    const val target = compile
}

object AndroidClient {
    const val appId = "com.axkov.moviepick"
    const val versionCode = 1
    const val versionName = "1.0"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
}

object BuildPlugins {
    object Versions {
        const val gradleVersion = "7.0.2"
        const val navigation = "2.3.5"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.stdLib}"
    const val navSafeArgsGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinParcelize = "kotlin-parcelize"
    const val navigationSafeArgsKotlin = "androidx.navigation.safeargs.kotlin"
}

object Libraries {
    private object Versions {
        const val coreKtx = "1.6.0"

        // Android
        const val appCompat = "1.3.1"
        const val fragmentKtx = "1.3.6"
        const val constraintLayout = "2.1.0"
        const val navigation = "2.3.5"
        const val lifecycle = "2.3.1"
        const val room = "2.3.0"
        const val material = "1.4.0"

        // Network
        const val retrofit = "2.9.0"
        const val okHttpLoggingInterceptor = "4.9.1"
        const val glide = "4.12.0"

        // RxJava
        const val rxJava = "3.1.1"
        const val rxAndroid = "3.0.0"

        // Dagger
        const val dagger = "2.38.1"

        // Timber
        const val timber = "5.0.1"
    }

    // Kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Kotlin.stdLib}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"

    // Android
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"

    const val material = "com.google.android.material:material:${Versions.material}"

    // Navigation
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // Lifecycle
    const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"

    // Room
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRxJava = "androidx.room:room-rxjava3:${Versions.room}"

    // Network
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val adapterRxJava = "com.squareup.retrofit2:adapter-rxjava3:${Versions.retrofit}"
    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptor}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    // RxJava 3
    const val rxJava = "io.reactivex.rxjava3:rxjava:${Versions.rxJava}"
    const val rxAndroid = "io.reactivex.rxjava3:rxandroid:${Versions.rxAndroid}"

    // Dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    // Timber
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
}

object TestLibraries {
    private object Versions {
        const val junit4 = "4.13.2"
        const val extJunit = "1.1.2"
        const val espressoCore = "3.4.0"
    }

    const val junit = "junit:junit:${Versions.junit4}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espressoCore}"
}