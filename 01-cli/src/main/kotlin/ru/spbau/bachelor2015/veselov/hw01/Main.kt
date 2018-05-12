package ru.spbau.bachelor2015.veselov.hw01

import ru.spbau.bachelor2015.veselov.hw01.commands.StandardCommandExecutor
import ru.spbau.bachelor2015.veselov.hw01.environment.Environment
import ru.spbau.bachelor2015.veselov.hw01.utilities.*

fun main(args: Array<String>) {
    val environment = Environment()
    environment.registerUtility("cat", CatUtility)
    environment.registerUtility("echo", EchoUtility)
    environment.registerUtility("wc", WcUtility)
    environment.registerUtility("pwd", PwdUtility)
    environment.registerUtility("grep", GrepUtility)
    environment.registerUtility("exit", ExitUtility)
    environment.registerUtility("ls", LsUtility)
    environment.registerUtility("cd", CdUtility)

    val cli =
        CommandLineInterface(
            ExecutionSystem(StandardCommandAssembler, StandardCommandExecutor, environment)
        )

    cli.run()
}
