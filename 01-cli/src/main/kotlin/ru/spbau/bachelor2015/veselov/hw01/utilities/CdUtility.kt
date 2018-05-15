package ru.spbau.bachelor2015.veselov.hw01.utilities

import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import ru.spbau.bachelor2015.veselov.hw01.WorkingDirectory
import ru.spbau.bachelor2015.veselov.hw01.environment.Utility

private const val homeProperty = "user.home"

/**
 * Cd utility sets the pointed directory as the working directory.
 */
object CdUtility: Utility {
    private const val TRY_TO_EXIT = false
    const val OUTPUT = ""
    const val MSG_NO_DIR = "Error: %s is not a directory.\n"

    override fun execute(args: List<String>, input: String, workingDirectory: WorkingDirectory):
            ExecutionResult {
        val pathDelta = if (args.isEmpty()) System.getProperty(homeProperty) else args[0]
        val success = workingDirectory.update(pathDelta)
        if (!success) {
            return ExecutionResult(MSG_NO_DIR.format(pathDelta), TRY_TO_EXIT)
        }
        return ExecutionResult(OUTPUT, TRY_TO_EXIT)
    }
}