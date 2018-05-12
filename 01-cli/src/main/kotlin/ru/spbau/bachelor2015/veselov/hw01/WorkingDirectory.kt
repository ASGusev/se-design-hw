package ru.spbau.bachelor2015.veselov.hw01

import java.nio.file.Path
import java.nio.file.Paths

class WorkingDirectory(
        private var path: Path
) {
    constructor() : this(Paths.get(".").toAbsolutePath().normalize())

    fun getPath() = path

    fun resolve(relativePath: String): Path = path.resolve(relativePath)

    fun update(pathDelta: String) {
        path = path.resolve(pathDelta).normalize()
    }
}