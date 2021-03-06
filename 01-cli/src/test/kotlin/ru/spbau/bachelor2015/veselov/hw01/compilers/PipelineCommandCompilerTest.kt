package ru.spbau.bachelor2015.veselov.hw01.compilers

import org.hamcrest.CoreMatchers
import org.junit.Assert.*
import org.junit.Test
import ru.spbau.bachelor2015.veselov.hw01.commands.Command
import ru.spbau.bachelor2015.veselov.hw01.commands.PipelineCommand
import ru.spbau.bachelor2015.veselov.hw01.commands.UtilityCommand
import ru.spbau.bachelor2015.veselov.hw01.preprocessing.CommandCharacter
import ru.spbau.bachelor2015.veselov.hw01.tokenization.StandardTokenizer
import ru.spbau.bachelor2015.veselov.hw01.tokenization.StringToken

class PipelineCommandCompilerTest {
    private val compiler =
            PipelineCommandCompiler.delegateTo(UtilityCommandCompiler(StandardTokenizer))

    @Test
    fun simplePipelineCommand() {
        val commandString = listOf(
            CommandCharacter('a', false),
            CommandCharacter(' ', false),
            CommandCharacter.pipe,
            CommandCharacter(' ', false),
            CommandCharacter('b', false)
        )

        val command =
            PipelineCommand(
                UtilityCommand(
                    StringToken("a"),
                    listOf()
                ),
                UtilityCommand(
                    StringToken("b"),
                    listOf()
                )
            ) as Command

        assertThat(
            compiler.compile(commandString),
            CoreMatchers.`is`(CoreMatchers.equalTo(command))
        )
    }
}
