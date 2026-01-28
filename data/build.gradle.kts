plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)


    alias(libs.plugins.ksp)
    alias(libs.plugins.serialization)
}

android {
    namespace = "org.coffeebreak.data"
    compileSdk = 36

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    androidResources{
        noCompress.add("tflite")
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11

    }
    kotlinOptions {
        jvmTarget = "11"
    }
}
kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-opt-in=kotlinx.datetime.ExperimentalTime", "-opt-in=kotlin.time.ExperimentalTime")
    }
}

dependencies {
    implementation(project(":domain"))


    implementation(libs.zxing.core)
    implementation(libs.androidx.security.crypto)

    implementation(libs.room)
    ksp(libs.room.compiler)

    implementation(libs.tensorflow.lite)
    implementation(libs.tensorflow.lite.support)

    implementation("org.tensorflow:tensorflow-lite-task-text:0.4.4")
    implementation("org.tensorflow:tensorflow-lite-gpu-delegate-plugin:0.4.4")
    implementation("org.tensorflow:tensorflow-lite-gpu:2.9.0")

    api(libs.supabase.compose.auth)
    implementation(libs.supabase.auth)
    implementation(libs.supabase.postgrest)
    implementation(libs.supabase.storage)
    implementation(libs.supabase.realtime)
    implementation(libs.serialization)

    // Ktor
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.cio)















    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}