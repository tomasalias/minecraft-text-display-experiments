package com.heledron.text_display_experiments.commands

import com.heledron.text_display_experiments.filesystem.FileSystem
import com.heledron.text_display_experiments.display.BitmapDisplay

object UnixCommands {
    private val fileSystem = FileSystem()
    private val bitmapDisplay = BitmapDisplay()

    fun executeCommand(command: String, params: Array<String>): String {
        return when (command) {
            "cd" -> cd(params)
            "mkdir" -> mkdir(params)
            "rm" -> rm(params)
            "clear" -> clear()
            else -> "Error: Command not found"
        }
    }

    private fun cd(params: Array<String>): String {
        if (params.isEmpty()) {
            return "Error: No directory specified"
        }
        val path = params[0]
        return if (fileSystem.changeDirectory(path)) {
            "Changed directory to $path"
        } else {
            "Error: Invalid directory"
        }
    }

    private fun mkdir(params: Array<String>): String {
        if (params.isEmpty()) {
            return "Error: No directory name specified"
        }
        val dirName = params[0]
        return if (fileSystem.createDirectory(dirName)) {
            "Directory $dirName created"
        } else {
            "Error: Directory already exists"
        }
    }

    private fun rm(params: Array<String>): String {
        if (params.isEmpty()) {
            return "Error: No file or directory specified"
        }
        val path = params[0]
        return if (fileSystem.remove(path)) {
            "Removed $path"
        } else {
            "Error: Invalid file or directory"
        }
    }

    private fun clear(): String {
        bitmapDisplay.clear()
        return "Display cleared"
    }
}
