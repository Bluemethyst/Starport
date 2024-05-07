import kotlinx.coroutines.*
import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) = runBlocking {
    val jobs = args.map { arg ->
        launch {
            val process = when {
                arg.endsWith(".py") -> ProcessBuilder("python", arg).start()
                arg.endsWith(".js") -> ProcessBuilder("node", arg).start()
                else -> throw IllegalArgumentException("Unsupported file type: $arg")
            }
            val reader = BufferedReader(InputStreamReader(process.inputStream))
            reader.lines().forEach { line -> println(line) }
            process.waitFor()
        }
    }
    jobs.joinAll()
}