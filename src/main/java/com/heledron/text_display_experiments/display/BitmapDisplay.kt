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

    private fun getCharacterBitmap(char: Char): Array<Array<Boolean>> {
        val charMap = mapOf(
            'A' to arrayOf(
                arrayOf(false, true, false),
                arrayOf(true, false, true),
                arrayOf(true, true, true),
                arrayOf(true, false, true),
                arrayOf(true, false, true)
            ),
            'B' to arrayOf(
                arrayOf(true, true, false),
                arrayOf(true, false, true),
                arrayOf(true, true, false),
                arrayOf(true, false, true),
                arrayOf(true, true, false)
            ),
            'C' to arrayOf(
                arrayOf(false, true, true),
                arrayOf(true, false, false),
                arrayOf(true, false, false),
                arrayOf(true, false, false),
                arrayOf(false, true, true)
            ),
            'D' to arrayOf(
                arrayOf(true, true, false),
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, true, false)
            ),
            'E' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(true, false, false),
                arrayOf(true, true, false),
                arrayOf(true, false, false),
                arrayOf(true, true, true)
            ),
            'F' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(true, false, false),
                arrayOf(true, true, false),
                arrayOf(true, false, false),
                arrayOf(true, false, false)
            ),
            'G' to arrayOf(
                arrayOf(false, true, true),
                arrayOf(true, false, false),
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(false, true, true)
            ),
            'H' to arrayOf(
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, true, true),
                arrayOf(true, false, true),
                arrayOf(true, false, true)
            ),
            'I' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(false, true, false),
                arrayOf(false, true, false),
                arrayOf(false, true, false),
                arrayOf(true, true, true)
            ),
            'J' to arrayOf(
                arrayOf(false, false, true),
                arrayOf(false, false, true),
                arrayOf(false, false, true),
                arrayOf(true, false, true),
                arrayOf(false, true, false)
            ),
            'K' to arrayOf(
                arrayOf(true, false, true),
                arrayOf(true, true, false),
                arrayOf(true, false, false),
                arrayOf(true, true, false),
                arrayOf(true, false, true)
            ),
            'L' to arrayOf(
                arrayOf(true, false, false),
                arrayOf(true, false, false),
                arrayOf(true, false, false),
                arrayOf(true, false, false),
                arrayOf(true, true, true)
            ),
            'M' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(true, true, true),
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, false, true)
            ),
            'N' to arrayOf(
                arrayOf(true, false, true),
                arrayOf(true, true, true),
                arrayOf(true, true, true),
                arrayOf(true, false, true),
                arrayOf(true, false, true)
            ),
            'O' to arrayOf(
                arrayOf(false, true, false),
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(false, true, false)
            ),
            'P' to arrayOf(
                arrayOf(true, true, false),
                arrayOf(true, false, true),
                arrayOf(true, true, false),
                arrayOf(true, false, false),
                arrayOf(true, false, false)
            ),
            'Q' to arrayOf(
                arrayOf(false, true, false),
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, true, true),
                arrayOf(false, true, true)
            ),
            'R' to arrayOf(
                arrayOf(true, true, false),
                arrayOf(true, false, true),
                arrayOf(true, true, false),
                arrayOf(true, true, false),
                arrayOf(true, false, true)
            ),
            'S' to arrayOf(
                arrayOf(false, true, true),
                arrayOf(true, false, false),
                arrayOf(false, true, false),
                arrayOf(false, false, true),
                arrayOf(true, true, false)
            ),
            'T' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(false, true, false),
                arrayOf(false, true, false),
                arrayOf(false, true, false),
                arrayOf(false, true, false)
            ),
            'U' to arrayOf(
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(false, true, false)
            ),
            'V' to arrayOf(
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(false, true, false),
                arrayOf(false, true, false)
            ),
            'W' to arrayOf(
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, true, true),
                arrayOf(true, true, true)
            ),
            'X' to arrayOf(
                arrayOf(true, false, true),
                arrayOf(false, true, false),
                arrayOf(false, true, false),
                arrayOf(false, true, false),
                arrayOf(true, false, true)
            ),
            'Y' to arrayOf(
                arrayOf(true, false, true),
                arrayOf(false, true, false),
                arrayOf(false, true, false),
                arrayOf(false, true, false),
                arrayOf(false, true, false)
            ),
            'Z' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(false, false, true),
                arrayOf(false, true, false),
                arrayOf(true, false, false),
                arrayOf(true, true, true)
            ),
            '0' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, true, true)
            ),
            '1' to arrayOf(
                arrayOf(false, true, false),
                arrayOf(true, true, false),
                arrayOf(false, true, false),
                arrayOf(false, true, false),
                arrayOf(true, true, true)
            ),
            '2' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(false, false, true),
                arrayOf(true, true, true),
                arrayOf(true, false, false),
                arrayOf(true, true, true)
            ),
            '3' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(false, false, true),
                arrayOf(true, true, true),
                arrayOf(false, false, true),
                arrayOf(true, true, true)
            ),
            '4' to arrayOf(
                arrayOf(true, false, true),
                arrayOf(true, false, true),
                arrayOf(true, true, true),
                arrayOf(false, false, true),
                arrayOf(false, false, true)
            ),
            '5' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(true, false, false),
                arrayOf(true, true, true),
                arrayOf(false, false, true),
                arrayOf(true, true, true)
            ),
            '6' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(true, false, false),
                arrayOf(true, true, true),
                arrayOf(true, false, true),
                arrayOf(true, true, true)
            ),
            '7' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(false, false, true),
                arrayOf(false, true, false),
                arrayOf(false, true, false),
                arrayOf(false, true, false)
            ),
            '8' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(true, false, true),
                arrayOf(true, true, true),
                arrayOf(true, false, true),
                arrayOf(true, true, true)
            ),
            '9' to arrayOf(
                arrayOf(true, true, true),
                arrayOf(true, false, true),
                arrayOf(true, true, true),
                arrayOf(false, false, true),
                arrayOf(true, true, true)
            )
        )
        return charMap[char] ?: arrayOf(
            arrayOf(false, false, false),
            arrayOf(false, false, false),
            arrayOf(false, false, false),
            arrayOf(false, false, false),
            arrayOf(false, false, false)
        )
    }

    private fun renderCharacter(x: Int, y: Int, char: Char) {
        val charBitmap = getCharacterBitmap(char)
        for (i in charBitmap.indices) {
            for (j in charBitmap[i].indices) {
                if (charBitmap[i][j]) {
                    updateDisplay(x + j, y + i, Color.WHITE)
                }
            }
        }
    }

    fun renderString(x: Int, y: Int, text: String) {
        var currentX = x
        for (char in text) {
            renderCharacter(currentX, y, char)
            currentX += 4 // Adjust spacing as needed
        }
    }
}
