package com.heledron.text_display_experiments.filesystem

import java.io.File

class FileSystem {
    private val rootDirectory = File("plugins")
    private var currentDirectory = rootDirectory

    init {
        if (!rootDirectory.exists()) {
            rootDirectory.mkdirs()
        }
    }

    fun changeDirectory(path: String): Boolean {
        val newDirectory = File(currentDirectory, path)
        return if (newDirectory.exists() && newDirectory.isDirectory) {
            currentDirectory = newDirectory
            true
        } else {
            false
        }
    }

    fun createDirectory(dirName: String): Boolean {
        val newDirectory = File(currentDirectory, dirName)
        return if (!newDirectory.exists()) {
            newDirectory.mkdirs()
            true
        } else {
            false
        }
    }

    fun remove(path: String): Boolean {
        val fileOrDirectory = File(currentDirectory, path)
        return if (fileOrDirectory.exists()) {
            fileOrDirectory.deleteRecursively()
            true
        } else {
            false
        }
    }
}
