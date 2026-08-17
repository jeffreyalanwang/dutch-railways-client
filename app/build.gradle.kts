import org.gradle.kotlin.dsl.kotlin
import java.lang.module.ModuleFinder.compose

plugins {
    alias(androidLibs.plugins.android.application)
    alias(androidLibs.plugins.kotlin.compose)
    alias(androidLibs.plugins.kotlin.parcelize)
    alias(androidLibs.plugins.kotlin.serialization)
    alias(androidLibs.plugins.secrets)
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
    implementation(platform(androidLibs.androidx.compose.bom))
    androidTestImplementation(platform(androidLibs.androidx.compose.bom))
    implementation(androidLibs.bundles.androidx.compose)
    testImplementation(androidLibs.bundles.androidx.compose.test)
    debugImplementation(androidLibs.bundles.androidx.compose.tooling)

    implementation(androidLibs.androidx.activity.compose)
    implementation(androidLibs.bundles.androidx.navigation3)
    implementation(androidLibs.androidx.datastore)
    implementation(androidLibs.androidx.core.ktx)
    implementation(androidLibs.androidx.lifecycle.runtime.ktx)
    implementation(androidLibs.androidx.appcompat)
    implementation(androidLibs.kotlinx.collections.immutable)
    implementation(androidLibs.kotlinx.serialization.json)
    implementation(androidLibs.kotlin.reflect)
    implementation(androidLibs.kotlinx.datetime)

    implementation(androidLibs.maps.compose)
    implementation(androidLibs.reorderable)
    implementation("com.jeffreyalanwang.dutchrailways.lib:schema")

    testImplementation(androidLibs.junit)
    testImplementation(androidLibs.mockk)
    testImplementation(androidLibs.kotlinx.coroutines.test)
    androidTestImplementation(androidLibs.mockkandroid)
    androidTestImplementation(androidLibs.androidx.compose.ui.test)
    androidTestImplementation(androidLibs.androidx.espresso.core)
    androidTestImplementation(androidLibs.androidx.junit)
    androidTestImplementation(androidLibs.androidx.uiautomator)
}