package dev.bluemethyst.starport

import java.io.File
import dev.bluemethyst.starport.data.Config
import dev.bluemethyst.starport.script.ScriptManager
import io.github.xn32.json5k.Json5
import kotlinx.serialization.decodeFromString

fun main() {
    val configFilePath = "config.json5"
    val config: Config = Json5.decodeFromString<Config>(File(configFilePath).readText())

    val scriptManager = ScriptManager()
    scriptManager.startScript(config)
    scriptManager.monitorScript(config.name)

    Runtime.getRuntime().addShutdownHook(Thread {
        scriptManager.stopScript(config.name)
    })
}