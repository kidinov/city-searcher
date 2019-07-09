const val KOTLIN_VERSION = "1.3.40"
const val COROUTINS_VERSION = "1.3.0-M2"
const val KOIN_VERSION = "2.0.1"
const val ANDROIDX_VERSION = "1.0.2"

const val GRADLE_VERSION = "3.4.1"

const val JUNIT_VERSION = "4.12"
const val TEST_RUNNER = "1.2.0"

object Lib {
    val koltin_std = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$KOTLIN_VERSION"
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINS_VERSION"
    val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$COROUTINS_VERSION"
    val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$COROUTINS_VERSION"

    val koin = "org.koin:koin-core:$KOIN_VERSION"
    val koin_android = "org.koin:koin-android-scope:$KOIN_VERSION"
    val app_compat = "androidx.appcompat:appcompat:$ANDROIDX_VERSION"
    val core_ktx = "androidx.core:core-ktx:$ANDROIDX_VERSION"
    val gson = "com.google.code.gson:gson:2.8.5"
    val constraint_laoyout = "com.android.support.constraint:constraint-layout:1.1.2"
    val material_design = "com.google.android.material:material:1.1.0-alpha07"
    val recycler_view = "androidx.recyclerview:recyclerview:1.1.0-beta01"

    val junit = "junit:junit:$JUNIT_VERSION"
    val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:2.1.0"
    val kluent = "org.amshove.kluent:kluent-android:1.50"
    val test_runner = "androidx.test:runner:$TEST_RUNNER"
}

object Plugins {
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$KOTLIN_VERSION"
    val gradle = "com.android.tools.build:gradle:$GRADLE_VERSION"
}