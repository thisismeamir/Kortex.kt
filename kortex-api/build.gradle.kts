import org.gradle.api.tasks.compile.JavaCompile
import org.jetbrains.kotlin.daemon.common.usedMemory

plugins {
    kotlin("jvm") version "2.0.21"
    kotlin("plugin.serialization") version "1.8.0" // or your Kotlin version
    `maven-publish`
    signing
}

group = "io.github.thisismeamir.kortex"
version = "0.1"

val pomGroupId = group
val pomVersion = version
val pomArtifactId = "kortexapi"
val pomArtifactName = "KortexClientApi"
val jdkCompileVersion = 17

repositories {
    mavenCentral()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.release.set(17)
}

val sourceCompatibility = "$jdkCompileVersion"
val targetCompatibility = "$jdkCompileVersion"

java {
    withJavadocJar()
    withSourcesJar()
    toolchain {
        languageVersion.set(JavaLanguageVersion.of("$jdkCompileVersion"))
    }
}

tasks.javadoc {
    if (JavaVersion.current().isJava9Compatible) {
        (options as StandardJavadocDocletOptions).addBooleanOption("html5", true)
    }
}

dependencies {
    implementation("io.ktor:ktor-client-core:2.3.4")
    implementation("io.ktor:ktor-client-cio:2.3.4")
    implementation("io.ktor:ktor-client-content-negotiation:2.3.4")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.4")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    testImplementation("io.ktor:ktor-client-mock:2.3.4")
    testImplementation(kotlin("test"))
    implementation("io.ktor:ktor-server-core:2.3.4")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(17)
}

/* publish to Maven Central (with maven publish plugin)
* https://s01.oss.sonatype.org/#welcome
* https://github.com/yourname/yourapp
* */
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = pomGroupId.toString()
            artifactId = pomArtifactId
            version = pomVersion.toString()
            artifact(tasks.getByName("jar"))
            pom {
                name.set(pomArtifactName)
                description.set("Kortex API")
                url.set("https://thisismeamir.github.io/Kortex.cpp")
                licenses {
                    license {
                        name.set("GPL v3.0 License")
                        url.set("https://www.gnu.org/licenses/gpl-3.0.en.html")
                    }
                }
                developers {
                    developer {
                        id.set("thisismeamir")
                        name.set("Amir H. Ebrahimnezhad")
                        email.set("thisismeamir@oulook.com")
                    }
                }
                scm {
                    connection.set("scm:git:git://github.com/thisismeamir/Kortex.cpp.git")
                    developerConnection.set("scm:git:ssh://github.com/thisismeamir/Kortex.cpp.git")
                    url.set("scm:git:https://github.com/thisismeamir/kortex.cpp.git")
                }
            }
        }
    }
    repositories {
        maven {
            name = "OSSRH"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = findProperty("username").toString()
                password = findProperty("password").toString()
            }
        }
    }
}

signing {
    useGpgCmd()
    sign(publishing.publications["mavenJava"])
}