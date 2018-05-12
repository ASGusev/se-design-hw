package ru.spbau.bachelor2015.veselov.hw01.utilities

import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import ru.spbau.bachelor2015.veselov.hw01.WorkingDirectory
import ru.spbau.bachelor2015.veselov.hw01.environment.Utility
import java.nio.file.Files
import java.util.stream.Collectors

/**
 * Ls utility returns a list of files and folders inside the pointed folder.
 * If no arguments are passed, the working directory is used.
 */
object LsUtility: Utility {
    private const val TRY_TO_EXIT = false
    private const val MSG_NO_DIR = "Error: %s is not a directory.\n"

    override fun execute(args: List<String>, input: String, workingDirectory: WorkingDirectory): ExecutionResult {
        val targetPath = if (args.isEmpty()) workingDirectory.getPath()
                else workingDirectory.resolve(args[0])
        if (!Files.isDirectory(targetPath)) {
            return ExecutionResult(MSG_NO_DIR.format(args[0]), TRY_TO_EXIT)
        }
        val output = Files.list(targetPath)
                .map({ targetPath.relativize(it) })
                .map({ it.toString() })
                .collect(Collectors.joining("\n", "", "\n"));
        return ExecutionResult(output, TRY_TO_EXIT)
    }
}