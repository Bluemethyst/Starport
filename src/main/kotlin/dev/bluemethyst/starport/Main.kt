package dev.bluemethyst.starport

import java.io.File
import dev.bluemethyst.starport.data.Config
import dev.bluemethyst.starport.runs.Run
import io.github.xn32.json5k.Json5
import kotlinx.serialization.decodeFromString

fun main() {
    val configFilePath = "A:\\Starport\\config.sp"
    val config: Config = Json5.decodeFromString<Config>(File(configFilePath).readText())

    println(config)
    val server = Run(config)
    server.run()
}