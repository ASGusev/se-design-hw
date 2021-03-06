package ru.spbau.bachelor2015.veselov.hw01.preprocessing

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.*
import org.junit.Test

class StandardCommandStringPreprocessorTest {
    @Test
    fun emptyString() {
        testString("", listOf())
    }

    @Test
    fun simpleString() {
        testString("ab", listOf(
                CommandCharacter('a', false),
                CommandCharacter('b', false)
        ))
    }

    @Test
    fun strongEscaping() {
        testString("a'$'", listOf(
                CommandCharacter('a', false),
                CommandCharacter('\'', false),
                CommandCharacter('$', true),
                CommandCharacter('\'', false)
        ))
    }

    @Test
    fun weakEscaping() {
        testString("a\"\$b\"", listOf(
                CommandCharacter('a', false),
                CommandCharacter('"', false),
                CommandCharacter('$', false),
                CommandCharacter('b', true),
                CommandCharacter('"', false)
        ))
    }

    @Test
    fun quotesInsideStrongEscaping() {
        testString("a'\"\"'", listOf(
                CommandCharacter('a', false),
                CommandCharacter('\'', false),
                CommandCharacter('"', true),
                CommandCharacter('"', true),
                CommandCharacter('\'', false)
        ))
    }

    @Test(expected = UnmatchedQuoteException::class)
    fun unmatchedSingleQuote() {
        StandardCommandStringPreprocessor.process("abc'def")
    }

    @Test(expected = UnmatchedQuoteException::class)
    fun unmatchedDoubkeQuote() {
        StandardCommandStringPreprocessor.process("abc'def")
    }

    private fun testString(input: String, output: CommandString) {
        assertThat(StandardCommandStringPreprocessor.process(input), `is`(equalTo(output)))
    }
}