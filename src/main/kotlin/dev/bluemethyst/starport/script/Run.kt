package dev.bluemethyst.starport.script

import dev.bluemethyst.starport.data.Config
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

class Run(private val config: Config) {
    fun run() = runBlocking {
        launch {
            val process = ProcessBuilder(config.runCommand.split(" "))
                .directory(File(config.workingDir))
                .redirectOutput(ProcessBuilder.Redirect.INHERIT)
                .start()

            process.waitFor()
            println("Process exited with code ${process.exitValue()}")
        }
    }
}