plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0.0"
        vectorDrawables.useSupportLibrary = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true

        javaCompileOptions {
            annotationProcessorOptions {
                arguments.plusAssign(
                    hashMapOf(
                        "room.schemaLocation" to "$projectDir/schemas",
                        "room.incremental" to "true",
                        "room.expandProjection" to "true"
                    )
                )
            }
        }
    }

    buildFeatures.dataBinding = true

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "AES_SECRET",  "\"asdkshEDBUFUN64H\$\$lkKR\"")

        }

        getByName("debug") {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            buildConfigField("String", "AES_SECRET",  "\"asdkshEDBUFUN64H\$\$lkKR\"")


        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        exclude("META-INF/*.kotlin_module")
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Kotlin
    api("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.4.21")

    // Coroutines
    api("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.3")
    api("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.3")

    // Android
    api("androidx.appcompat:appcompat:1.3.0")
    api("androidx.activity:activity-ktx:1.2.3")
    api("androidx.fragment:fragment-ktx:1.3.5")
    api("androidx.core:core-ktx:1.5.0")
    api("androidx.constraintlayout:constraintlayout:2.0.4")
    api("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

    // Architecture Components
    api("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    api("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")

    // Room components
    /*api(Room.runtime)
    api(Room.ktx)
    implementation("com.google.firebase:firebase-config-ktx:20.0.4")
    kapt(Room.compiler)*/

    // Material Design
    api("com.google.android.material:material:1.3.0")
//    api(Dependencies.materialDialog)

    // Coil-kt
//    api("io.coil-kt:coil:0.9.5")

    // Retrofit
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:converter-moshi:2.7.2")
    // Logging Interceptor
    api("com.squareup.okhttp3:logging-interceptor:4.8.0")

    // RxJava
    api("io.reactivex:rxjava:1.3.4")
    api("io.reactivex.rxjava2:rxjava:2.1.16")
    api("io.reactivex.rxjava2:rxandroid:2.0.2")

    // Moshi
    api("com.squareup.moshi:moshi-kotlin:1.9.3")
    api("com.squareup.moshi:moshi-kotlin-codegen:1.9.3")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.9.3")
    api("com.google.code.gson:gson:2.8.6")

    //Navigation
    api("androidx.navigation:navigation-runtime-ktx:2.3.5")
    api ("androidx.navigation:navigation-fragment-ktx:2.3.5")
    api ("androidx.navigation:navigation-ui-ktx:2.3.5")

    // Koin
    api("org.koin:koin-android:2.0.1")
    api("org.koin:koin-androidx-viewmodel:2.0.1")
    api("org.koin:koin-androidx-scope:2.0.1")

    // Timber
    api("com.jakewharton.timber:timber:4.7.1")

    // ImagePicker
//    api(ImagePicker.picker)

    // Secured Preference
    api("androidx.security:security-crypto:1.1.0-alpha03")

    api("com.intuit.sdp:sdp-android:1.0.6")

    api("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    // Fireabase dependencies
    api(platform("com.google.firebase:firebase-bom:26.7.0"))
    api("com.google.firebase:firebase-analytics-ktx")
    api("com.google.firebase:firebase-crashlytics-ktx")
    api("com.google.firebase:firebase-messaging-ktx")

    api ("id.zelory:compressor:2.1.0")

    // Testing
    testImplementation("androidx.arch.core:core-testing:2.1.0")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.9")
//    testImplementation(Testing.room)
    testImplementation("com.squareup.okhttp3:mockwebserver:4.4.0")
    testImplementation("junit:junit:4.13.2")

    // Android Testing
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}