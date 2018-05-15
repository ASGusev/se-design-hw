package ru.spbau.bachelor2015.veselov.hw01

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

/**
 * WorkingDirectory class stores the path to the current working directory.
 */
class WorkingDirectory(
        private var path: Path
) {
    /**
     * Creates an instance with the program launch directory.
     */
    constructor() : this(Paths.get(".").toAbsolutePath().normalize())

    /**
     * Returns a path to the current working directory.
     */
    fun getPath() = path

    /**
     * Makes a path by adding the provided string to the working directory path.
     */
    fun resolve(relativePath: String): Path = path.resolve(relativePath).normalize()

    /**
     * Attempts to replace the working directory with the directory of provided path.
     * If the pointed directory does not exist, the function fails and returns false.
     * Otherwise returns true.
     */
    fun update(pathDelta: String): Boolean {
        val newPath = resolve(pathDelta)
        if (!Files.isDirectory(newPath)) {
            return false
        }
        path = newPath
        return true
    }
}