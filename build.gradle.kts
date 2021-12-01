// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val lifecycle_version by extra("2.2.0")
    val retrofit_version by extra("2.9.0")
    val kotlin_version by extra("1.6.0")
    val nav_version by extra("2.3.5")
    val hilt_version by extra("2.40.3")

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.0.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
