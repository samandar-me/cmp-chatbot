plugins {
    //trick: for the same plugin versions in all sub-modules
    kotlin("multiplatform").apply(false)
    id("com.android.application").apply(false)
    id("com.android.library").apply(false)
    id("org.jetbrains.compose").apply(false)
    //id("org.jetbrains.kotlin.native.cocoapods").apply(false)
}

//buildscript {
//    dependencies {
//        classpath("dev.icerock.moko:resources-generator:0.22.3")
//    }
//}

allprojects {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}