package ru.spbau.bachelor2015.veselov.hw01

import ru.spbau.bachelor2015.veselov.hw01.environment.Utility

object ConcatenationUtility : Utility {
    const val triesToExit: Boolean = false

    override fun execute(args: List<String>, input: String, workingDirectory: WorkingDirectory):
            ExecutionResult {
        return ExecutionResult(args.joinToString("") + input, triesToExit)
    }
}
