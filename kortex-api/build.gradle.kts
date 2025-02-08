plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "1.8.0" // or your Kotlin version
    `maven-publish`
}

group = "io.github.thisismeamir.kortex"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.ktor:ktor-client-core:2.3.4")
    implementation("io.ktor:ktor-client-cio:2.3.4")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    testImplementation("io.ktor:ktor-client-mock:2.0.0")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = "io.github.thisismeamir"
            artifactId = "kortex-api"
            version = "1.0.0"

            from(components["java"])
        }
    }
}