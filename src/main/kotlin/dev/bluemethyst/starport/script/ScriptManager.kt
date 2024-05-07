package dev.bluemethyst.starport.script

import dev.bluemethyst.starport.data.Config
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader

class ScriptManager {
    private val runningScripts: MutableMap<String, Process> = mutableMapOf()

    fun startScript(config: Config) = runBlocking {
        launch {
            val process = ProcessBuilder(config.runCommand, config.args)
                .directory(File(config.workingDir))
                .start()
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            reader.lines().forEach { line -> println(line) }
            val errorReader = BufferedReader(InputStreamReader(process.errorStream))
            errorReader.lines().forEach { line -> println(line) }
            process.waitFor()
        }
    }

    fun stopScript(name: String) {
        runningScripts[name]?.destroyForcibly()
        runningScripts.remove(name)
        println("Script $name stopped.")
    }

    fun monitorScript(name: String) {
        val process = runningScripts[name]
        if (process != null) {
            println("Script $name is running: ${process.isAlive}")
        } else {
            println("Script $name is not running.")
        }
    }
}