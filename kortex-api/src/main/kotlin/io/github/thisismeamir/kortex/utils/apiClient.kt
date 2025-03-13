package io.github.thisismeamir.kortex.utils

import io.github.thisismeamir.kortex.Kortex
import io.github.thisismeamir.kortex.client.infrastructure.ApiClient
import io.ktor.client.engine.cio.CIO

val apiClient = ApiClient(baseUrl = "http://127.0.0.1:39281", httpClientEngine = CIO.create())
val myService = apiClient.createService(Kortex::class)