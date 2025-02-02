import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("dagger.hilt.android.plugin")
    id("kotlin-kapt")
}

//read an API key (or any other configuration value)
// from a local.properties file.
// This is a common practice in Android development to keep sensitive information,
// such as API keys, out of version control (like Git) and to allow different configurations for different environments (e.g., development, staging, production).
val localProperties = File(rootProject.rootDir, "local.properties").reader().use {
    Properties().apply { load(it) }
}

val apiKey = localProperties.getProperty("API_KEY", "")

android {
    namespace = "com.example.moonsoon"
    compileSdk = 35

    defaultConfig {

        buildConfigField("String", "API_KEY", "\"$apiKey\"")
        applicationId = "com.example.moonsoon"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig =true
    }
}

dependencies {
    // Core Android dependencies
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))

    // Compose dependencies
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Retrofit and GSON
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation(libs.converter.gson)

    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)

    // Material Icons
    implementation("androidx.compose.material:material-icons-extended:1.7.6")

    //Material Icons Extended
    implementation("androidx.compose.material:material-icons-extended:1.7.6")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.8.5")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")
    kapt("androidx.hilt:hilt-compiler:1.1.0")

    // Testing dependencies
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    //viewmodel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.7")

    //androidsvg arr
    implementation(libs.androidsvg.aar)

}

// Hilt Kapt Configuration
kapt {
    correctErrorTypes = true
}
