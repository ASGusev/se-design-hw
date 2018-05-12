package ru.spbau.bachelor2015.veselov.hw01.utilities

import org.junit.*
import org.junit.rules.TemporaryFolder
import ru.spbau.bachelor2015.veselov.hw01.WorkingDirectory
import java.util.*

class CdUtilityTest {
    @Rule
    @JvmField
    val tmpDir = TemporaryFolder()
    private val innerDir = "foo"
    private val fakeDir = "bar"

    @Test fun simpleTest() {
        tmpDir.newFolder(innerDir)
        val wd = WorkingDirectory(tmpDir.root.toPath())
        val returned = CdUtility.execute(Collections.singletonList(innerDir), "", wd)
        Assert.assertEquals(CdUtility.OUTPUT, returned.output)
        Assert.assertEquals(false, returned.triesToExit)
    }

    @Test fun noArgsTest() {
        val homeDir = System.getProperty("user.home")
        val wd = WorkingDirectory(tmpDir.root.toPath())
        val returned = CdUtility.execute(Collections.emptyList(), "", wd)
        Assert.assertEquals(homeDir, wd.getPath().toString())
        Assert.assertEquals(CdUtility.OUTPUT, returned.output)
        Assert.assertEquals(false, returned.triesToExit)
    }

    @Test fun badDirTest() {
        val wd = WorkingDirectory()
        val returned = CdUtility.execute(Collections.singletonList(fakeDir), "", wd)
        Assert.assertEquals(CdUtility.MSG_NO_DIR.format(fakeDir), returned.output)
        Assert.assertEquals(false, returned.triesToExit)
    }
}