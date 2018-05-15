package ru.spbau.bachelor2015.veselov.hw01

import org.junit.Assert
import org.junit.Test
import org.junit.rules.TemporaryFolder
import java.nio.file.Path
import java.nio.file.Paths

class WorkingDirectoryTest {
    private val currentDir: Path = Paths.get(System.getProperty("user.dir"))
    private val innerName = "foo"
    private val absName = "/"

    @Test fun creationTest() {
        val workingDirectory = WorkingDirectory()
        Assert.assertEquals(currentDir, workingDirectory.getPath())
    }

    @Test fun resolveTest() {
        val workingDirectory = WorkingDirectory()
        val resolved = workingDirectory.resolve(innerName)
        val expected = currentDir.resolve(innerName)
        Assert.assertEquals(expected, resolved)
    }

    @Test fun resolveAbsoluteTest() {
        val workingDirectory = WorkingDirectory()
        val resolved = workingDirectory.resolve(absName)
        val expected = Paths.get(absName)
        Assert.assertEquals(expected, resolved)
    }

    @Test fun updateTest() {
        val tmpDir = TemporaryFolder()
        tmpDir.create()
        tmpDir.newFolder(innerName)
        val workingDirectory = WorkingDirectory(tmpDir.root.toPath())
        val success = workingDirectory.update(innerName)
        val expected = tmpDir.root.toPath().resolve(innerName)
        Assert.assertTrue(success)
        Assert.assertEquals(expected, workingDirectory.getPath())
        tmpDir.delete()
    }


    @Test fun updateAbsoluteTest() {
        val workingDirectory = WorkingDirectory()
        workingDirectory.update(absName)
        val expected = Paths.get(absName)
        Assert.assertEquals(expected, workingDirectory.getPath())
    }
}