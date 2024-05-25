plugins {
    kotlin("jvm") version "1.9.23"
}

group = "io.github.hamidsafdari"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jsoup:jsoup:1.17.2")
    implementation("org.xerial:sqlite-jdbc:3.45.3.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}