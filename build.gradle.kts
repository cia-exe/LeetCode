import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    //id("org.jetbrains.kotlinx.benchmark") version "0.3.1"       // for benchmark
    //id("org.jetbrains.kotlin.plugin.allopen") version "1.6.0"   // for benchmark

    application
}

group = "me.ken_wu"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test-junit5"))
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")

    //testImplementation("org.jetbrains.kotlinx:kotlinx-benchmark-runtime:0.3.1")   // for benchmark
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "MainKt"
}

/* // for benchmark
allOpen {
    annotation("org.openjdk.jmh.annotations.State")
}

benchmark {
    targets {
        register("test")
    }
}*/