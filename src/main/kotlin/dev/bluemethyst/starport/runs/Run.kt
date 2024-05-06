package dev.bluemethyst.starport.runs

import dev.bluemethyst.starport.data.Config
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.File

class Run(private val config: Config) {
    fun run() {
        val command = config.runCommand

        val process = ProcessBuilder(command.split(" ")).apply {
            directory(File(config.workingDir))
        }.start()

        // Add shutdown hook to destroy process when JVM is shutting down
        Runtime.getRuntime().addShutdownHook(Thread { process.destroy() })

        val reader = BufferedReader(InputStreamReader(process.inputStream))
        val errorReader = BufferedReader(InputStreamReader(process.errorStream))

        reader.forEachLine { println(it) }
        errorReader.forEachLine { System.err.println(it) }

        val exitCode = process.waitFor()
        println("Exited with code $exitCode")
    }
}