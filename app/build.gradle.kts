import jdk.internal.jshell.debug.InternalDebugControl.release
import org.gradle.kotlin.dsl.kotlin
import java.lang.module.ModuleFinder.compose

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.secrets)
}

kotlin {
    jvmToolchain(21)
    compilerOptions {
        optIn.add("kotlin.time.ExperimentalTime")
        optIn.add("androidx.compose.material3.ExperimentalMaterial3Api")
        optIn.add("androidx.compose.ui.test.ExperimentalTestApi")
        freeCompilerArgs.addAll("-P", "plugin:org.jetbrains.kotlin.parcelize:experimentalCodeGeneration=true")
        freeCompilerArgs.add("-XXLanguage:+ContextParameters")
        freeCompilerArgs.add("-XXLanguage:+NestedTypeAliases")
    }
}

android {
    namespace = "com.jeffreyalanwang.dutchrailways.client.android"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.jeffreyalanwang.dutchrailways.client.android"
        minSdk = 35
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isDebuggable = false
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isDebuggable = true
            isMinifyEnabled = false
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
        }
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.androidx.compose)
    testImplementation(libs.bundles.androidx.compose.test)
    debugImplementation(libs.bundles.androidx.compose.tooling)

    implementation(libs.androidx.activity.compose)
    implementation(libs.bundles.androidx.navigation3)
    implementation(libs.androidx.datastore)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.kotlinx.collections.immutable)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlinx.datetime)

    implementation(libs.maps.compose)
    implementation(libs.reorderable)
    implementation("com.jeffreyalanwang.dutchrailways.lib:schema")

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.mockkandroid)
    androidTestImplementation(libs.androidx.compose.ui.test)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.uiautomator)
}