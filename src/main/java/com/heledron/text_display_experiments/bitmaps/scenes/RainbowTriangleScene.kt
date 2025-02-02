package com.heledron.text_display_experiments.bitmaps.scenes

import com.heledron.text_display_experiments.bitmaps.FragmentData
import com.heledron.text_display_experiments.bitmaps.RenderBuffer
import com.heledron.text_display_experiments.bitmaps.drawTriangles
import com.heledron.text_display_experiments.bitmaps.meshes.RainbowTriangle
import com.heledron.text_display_experiments.bitmaps.shaders.RGB2DShader

class RainbowTriangleScene : Scene {
    val buffer = RenderBuffer(64, 64) { FragmentData(CLEAR_COLOR) }
    override fun getBitmap() = buffer.map { it.color }
    init {
        buffer.drawTriangles(RainbowTriangle, RGB2DShader, true)
    }
}