package dev.bluemethyst.starport.script

import com.lordcodes.turtle.ShellLocation
import com.lordcodes.turtle.shellRun
import dev.bluemethyst.starport.data.Config
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

class ScriptManager {
    private val runningScripts: MutableMap<String, Process> = mutableMapOf()

    fun startScript(config: Config) = runBlocking {
        launch {
            val cwd = ShellLocation.HOME.resolve(config.workingDir)
            val process = shellRun(config.runCommand)
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