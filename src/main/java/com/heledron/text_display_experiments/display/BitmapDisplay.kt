package com.heledron.text_display_experiments.display

import org.bukkit.Color
import org.bukkit.entity.Player

class BitmapDisplay {
    private val width = 64
    private val height = 32
    private val bitmap = Array(height) { Array(width) { Color.BLACK } }

    fun updateDisplay(x: Int, y: Int, color: Color) {
        if (x in 0 until width && y in 0 until height) {
            bitmap[y][x] = color
        }
    }

    fun clearDisplay() {
        for (y in 0 until height) {
            for (x in 0 until width) {
                bitmap[y][x] = Color.BLACK
            }
        }
    }

    fun renderToPlayer(player: Player) {
        // Implementation to render the bitmap to the player's display
    }

    fun printErrorMessage(message: String) {
        clearDisplay()
        // Implementation to print the error message to the bitmap display
    }
}
