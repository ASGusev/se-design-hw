package ru.spbau.bachelor2015.veselov.hw01.utilities

import org.apache.commons.io.FileUtils
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import ru.spbau.bachelor2015.veselov.hw01.ExecutionResult
import ru.spbau.bachelor2015.veselov.hw01.WorkingDirectory
import java.nio.charset.Charset

class WcUtilityTest {
    @Rule
    @JvmField
    val folder = TemporaryFolder()

    @Test
    fun oneFile() {
        val file = folder.newFile()

        FileUtils.writeStringToFile(file, "abc \n abd", Charset.defaultCharset())

        val executionResult =
                WcUtility.execute(listOf(file.absolutePath), "", WorkingDirectory())

        MatcherAssert.assertThat(
            executionResult,
            CoreMatchers.`is`(CoreMatchers.equalTo(ExecutionResult("1 2 9\n", false)))
        )
    }

    @Test
    fun inputStream() {
        testInputStream("a  b", "0 2 4")
    }

    @Test
    fun newLineEnd() {
        testInputStream("abc\n", "1 1 4")
    }

    private fun testInputStream(input: String, output: String) {
        val executionResult = WcUtility.execute(listOf(), input, WorkingDirectory())

        MatcherAssert.assertThat(
            executionResult,
            CoreMatchers.`is`(
                CoreMatchers.equalTo(ExecutionResult("$output\n", false)
            ))
        )
    }
}
