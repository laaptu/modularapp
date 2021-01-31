package org.ahivs.dependencies

object Version {
    const val kotlin = "1.3.72"
    const val gradleAndroid = "4.1.1"
    const val coroutines = "1.3.2"
    const val gson = "2.8.5"
    const val okhttp = "4.2.0"
    const val dagger = "2.24"
    const val retrofit = "2.6.1"

    object Android {
        const val appcompat = "1.1.0"
        const val lifecycleExtension = "2.1.0"
        const val material = "1.1.0-alpha10"
        const val constraintLayoutVersion = "1.1.3"
        const val androidxCore = "1.3.2"
        const val androidxActivity = "1.1.0-alpha03"
        const val androidxFragment = "1.2.0-alpha03"
    }

    //testlib version
    const val mockito = "2.25.0"
    const val junit5 = "5.5.1"
}

object GradlePlugin {
    const val android = "com.android.tools.build:gradle:${Version.gradleAndroid}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    const val junit5 = "de.mannodermaus.gradle.plugins:android-junit5:1.7.0.0"
}

object Libs {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Version.kotlin}"

    //retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava2:${Version.retrofit}"
    const val retrofitJsonConverter = "com.squareup.retrofit2:converter-gson:${Version.retrofit}"

    const val okHttpLoggingInterceptor =
        "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
    const val okHttpClient = "com.squareup.okhttp3:okhttp:${Version.okhttp}"

    const val gson = "com.google.code.gson:gson:${Version.gson}"

    const val dagger = "com.google.dagger:dagger:${Version.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
    const val daggerAndroid = "com.google.dagger:dagger-android:${Version.dagger}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Version.dagger}"
    const val daggerAndroidProcessor =
        "com.google.dagger:dagger-android-processor:${Version.dagger}"

    //android provided libs
    object Android {
        const val appcompat = "androidx.appcompat:appcompat:${Version.Android.appcompat}"
        const val material = "com.google.android.material:material:${Version.Android.material}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Version.Android.constraintLayoutVersion}"
        const val constraintLayoutSolver =
            "androidx.constraintlayout:constraintlayout-solver:${Version.Android.constraintLayoutVersion}"
        const val androidxCore = "androidx.core:core-ktx:${Version.Android.androidxCore}"
        const val androidxActivity =
            "androidx.activity:activity-ktx:${Version.Android.androidxActivity}"
        const val androidxFragment =
            "androidx.fragment:fragment-ktx:${Version.Android.androidxFragment}"
        const val lifecycleExtension =
            "androidx.lifecycle:lifecycle-extensions:${Version.Android.lifecycleExtension}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.Android.lifecycleExtension}"
    }
}

object TestLibs {
    const val mockitoCore = "org.mockito:mockito-core:${Version.mockito}"
    const val mockitoInline = "org.mockito:mockito-inline:${Version.mockito}"
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"

    const val junit5 = "org.junit.jupiter:junit-jupiter-api:${Version.junit5}"
    const val junit5Engine = "org.junit.jupiter:junit-jupiter-engine:${Version.junit5}"
    const val junit5Params = "org.junit.jupiter:junit-jupiter-params:${Version.junit5}"

    object Android {
        const val arch = "androidx.arch.core:core-testing:${Version.Android.lifecycleExtension}"
    }
}