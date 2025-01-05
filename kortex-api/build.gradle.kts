plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "1.5.31"

}

group = "com.thisismeamir"
version = "0.0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-client-core:2.3.4") // Core library
    implementation("io.ktor:ktor-client-cio:2.3.4")  // CIO engine
    implementation("io.ktor:ktor-client-content-negotiation:2.3.4") // Content negotiation
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4") // JSON serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")

    testImplementation("org.testng:testng:7.4.0")
    testImplementation("io.mockk:mockk:1.12.0")
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.5.31")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}