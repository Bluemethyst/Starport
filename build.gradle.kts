plugins {
    kotlin("jvm") version "1.9.23"
    kotlin("plugin.serialization") version "1.8.10"
}

group = "dev.bluemethyst"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.github.xn32:json5k:0.3.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
    implementation("com.lordcodes.turtle:turtle:0.10.0")

}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}