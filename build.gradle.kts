buildscript {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:3.5.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.50")
        //classpath("com.google.gms:google-services:4.3.2")
    }
}
allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}