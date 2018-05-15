package ru.spbau.bachelor2015.veselov.hw01.utilities

import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.rules.TemporaryFolder
import ru.spbau.bachelor2015.veselov.hw01.WorkingDirectory
import java.nio.file.Files
import java.util.*
import kotlin.collections.HashSet

class LsUtilityTest {
    private val tmpDir = TemporaryFolder()
    private val d1 = "foo"
    private val d2 = "bar"
    private val d11 = "buz"

    @Before fun prepareDir() {
        tmpDir.create()
        val p1 = tmpDir.newFolder(d1).toPath()
        tmpDir.newFolder(d2)
        Files.createDirectory(p1.resolve(d11))
    }

    @Test fun noArgsTest() {
        val workingDirectory = WorkingDirectory(tmpDir.root.toPath())
        val result = LsUtility.execute(Collections.emptyList(), "", workingDirectory)
        val listedDirs = result.output.split("\n").filter { it.isNotEmpty() }.sorted()
        Assert.assertEquals(listOf(d1, d2).sorted(), listedDirs)
    }

    @Test fun subDirTest() {
        val workingDirectory = WorkingDirectory(tmpDir.root.toPath())
        val result = LsUtility.execute(Collections.singletonList(d1), "", workingDirectory)
        val listedDirs = result.output.split("\n").filter { it.isNotEmpty() }
        Assert.assertEquals(listOf(d11), listedDirs)
    }

    @After fun removeDir() {
        tmpDir.delete()
    }
}