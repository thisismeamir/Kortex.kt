package com.thisismeamir

import Kortex
import io.ktor.client.call.*
import io.ktor.client.statement.*
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import responseObjects.StartModelResponse
import utils.startCortexCpp
import utils.CortexLogLevel
import kotlin.coroutines.CoroutineContext

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(): Unit = runBlocking {

    val kortex = Kortex(5555, CortexLogLevel.TRACE)
    val configuration = kortex.getConfigurations()
//    println(configuration.bodyAsText())

    val hardwareInfo = kortex.getHardwareInfo()
//    println(hardwareInfo.bodyAsText())

    val models = kortex.listModels()
//    println(models.bodyAsText())
    val startModel = kortex.startModel(model = "llama3.1:8b-gguf")
//    println(startModel.bodyAsText())
    println(Json.decodeFromString<StartModelResponse>(startModel.bodyAsText()).message)
    kortex.stop()





}