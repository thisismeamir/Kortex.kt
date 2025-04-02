# Kortex.kt

Kortex.kt is a client library that enables Kotlin programs to connect to Cortex.cpp, a Local AI API Platform, and use its functionalities to run and customize LLMs.

## Overview

Kortex provides an easy-to-use interface for interacting with Cortex.cpp, allowing you to manage models, perform resource management, and utilize the REST API provided by Cortex.cpp.

## Key Features

- Connect to Cortex.cpp and manage models
- Perform resource management operations
- Utilize the REST API provided by Cortex.cpp

## Installation

Ensure you have the `kotlinx-serialization-json` dependency in your `build.gradle.kts` file:

### Dependencies
```kotlin
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
```
