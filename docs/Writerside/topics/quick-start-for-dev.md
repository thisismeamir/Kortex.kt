# Quick Start For Developers

This guide will help you quickly set up and use the `kortex-api` project.

## Prerequisites

- JDK 17 or higher
- Gradle
- Internet connection to download dependencies

## Project Setup

1. **Clone the Repository**

   ```sh
   git clone https://github.com/thisismeamir/kortex-api.git
   cd kortex-api
   ```

2. **Build the Project**

   ```sh
   ./gradlew build
   ```

3. **Run Tests**

   ```sh
   ./gradlew test
   ```

## Usage Example

Here is a simple example to demonstrate how to use the `kortex-api` to create a chat completion.

1. **Create a Kotlin File**

   Create a new Kotlin file, e.g., `ChatCompletionExample.kt`.

2. **Add the Following Code**

   ```kotlin
   import io.github.thisismeamir.kortex.Kortex
   import io.github.thisismeamir.kortex.objs.completions.ChatCompletionRequest
   import io.github.thisismeamir.kortex.objs.completions.ChatCompletionRequestMessage
   import io.github.thisismeamir.kortex.objs.startmodel.StartModelRequest
   import kotlinx.coroutines.runBlocking

   fun main() = runBlocking {
       val kortex = Kortex()

       // Create a chat completion request
       val request = ChatCompletionRequest(
           messages = listOf(
               ChatCompletionRequestMessage(
                   role = "user",
                   content = "Hello, how are you?",
                   name = "Kid A"
               )
           ),
           model = kortex.getAllModels().last().id,
       )

       // Load the engine
       val engine = kortex.loadEngine("llama-cpp")

       // Start the model
       val model = kortex.startModel(StartModelRequest(
           model = kortex.getAllModels().last().id
       ))

       // Create chat completion
       val response = kortex.createChatCompletion(request)
       println(response.choices.first().message.content)
   }
   ```

3. **Run the Example**

   ```sh
   kotlinc -script ChatCompletionExample.kt
   ```

This will print the response from the chat completion API.

#### Dependencies

The project uses the following dependencies:

- Ktor Client
- Kotlinx Serialization
- JUnit for testing

These dependencies are defined in the `build.gradle.kts` file.

#### Additional Information

For more details, refer to the source code and the `build.gradle.kts` file for configuration and dependencies.