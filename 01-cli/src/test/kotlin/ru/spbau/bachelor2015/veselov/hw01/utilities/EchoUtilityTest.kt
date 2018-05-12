package ru.spbau.bachelor2015.veselov.hw01.utilities

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import ru.spbau.bachelor2015.veselov.hw01.WorkingDirectory

class EchoUtilityTest {
    @Test
    fun twoArgumentsTest() {
        val arg1 = "abc"
        val arg2 = "efg"

        val executionResult =
                EchoUtility.execute(listOf(arg1, arg2), "", WorkingDirectory())

        assertThat(
            executionResult,
            CoreMatchers.`is`(CoreMatchers.equalTo(
                ExecutionResult(arg1 + " " + arg2 + "\n", false)
            ))
        )
    }
}