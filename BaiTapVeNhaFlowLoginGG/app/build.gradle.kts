plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.uth.baitapvenhaflowlogingg"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.uth.baitapvenhaflowlogingg"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    kotlin {
        jvmToolchain(21)
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"
    }

    // Tuỳ chọn: loại bỏ thanh ActionBar mặc định
    packaging {
        resources.excludes.add("META-INF/DEPENDENCIES")
    }
}

dependencies {
    // Firebase
    implementation(platform("com.google.firebase:firebase-bom:33.5.0"))
    implementation("com.google.firebase:firebase-auth")
    implementation("com.google.firebase:firebase-messaging")

    // Google Sign-In
    implementation("com.google.android.gms:play-services-auth:21.2.0")

    // Core
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")

    // Jetpack Compose BOM (để đồng bộ version giữa các thư viện Compose)
    implementation(platform("androidx.compose:compose-bom:2024.09.01"))

    // Compose cơ bản
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.activity:activity-compose:1.9.2")

    // Material 3 (Jetpack Compose)
    implementation("androidx.compose.material3:material3:1.2.1")

    // Material Components (XML-based, dùng cho Theme.Material3.*)
    implementation("com.google.android.material:material:1.12.0")

    // Navigation Compose
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // ViewModel + LiveData Compose
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Image loading with Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Debug tools
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}
