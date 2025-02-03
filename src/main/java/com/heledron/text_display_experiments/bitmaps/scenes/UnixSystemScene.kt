package com.heledron.text_display_experiments.bitmaps.scenes

import com.heledron.text_display_experiments.utilities.Grid
import org.bukkit.Color
import org.bukkit.entity.Player
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class UnixSystemScene : Scene {
    private val height = 64
    private val bitmap = Grid((height * 2.0).toInt(), height) { Color.BLACK }
    override fun getBitmap() = bitmap

    private val commandOutput = mutableListOf<String>()

    fun handlePlayerChatInput(player: Player, message: String) {
        val command = message.trim()
        if (command.isNotEmpty()) {
            val output = executeUnixCommand(command)
            commandOutput.addAll(output)
            updateBitmapDisplay()
        }
    }

    private fun executeUnixCommand(command: String): List<String> {
        val output = mutableListOf<String>()
        try {
            val process = Runtime.getRuntime().exec(command)
            val inputStream = process.inputStream
            val errorStream = process.errorStream

            val inputReader = inputStream.bufferedReader()
            val errorReader = errorStream.bufferedReader()

            output.addAll(inputReader.readLines())
            output.addAll(errorReader.readLines())

            process.waitFor()
        } catch (e: Exception) {
            output.add("Error executing command: ${e.message}")
        }
        return output
    }

    private fun updateBitmapDisplay() {
        bitmap.setAll { Color.BLACK }
        val maxLines = bitmap.height
        val linesToDisplay = commandOutput.takeLast(maxLines)

        for ((index, line) in linesToDisplay.withIndex()) {
            val y = index
            for ((x, char) in line.withIndex()) {
                if (x < bitmap.width) {
                    bitmap[x to y] = Color.WHITE
                }
            }
        }
    }
}
