package ru.spbau.bachelor2015.veselov.hw01

private const val promptTemplate = "%s>"

/**
 * A REPL command line interface for execution system.
 */
class CommandLineInterface(private val system: ExecutionSystem) {
    private val prompt = "${system.workingDirectory}>"

    /**
     * Runs REPL cycle.
     */
    fun run() {
        while (true) {
            print(prompt)
            val input = readLine() ?: break

            try {
                val executionResult = system.execute(input)
                print(executionResult.output)

                if (executionResult.triesToExit) {
                    break
                }
            } catch (e: Exception) {
                println(e.message)
            }
        }
    }
}
