package com.heledron.text_display_experiments.bitmaps.meshes

import com.heledron.text_display_experiments.bitmaps.Mesh

object Water: Mesh {
	override val vertices = arrayOf(
		doubleArrayOf(-6.0, -5.0, -6.0, 1.0, 1.0),
		doubleArrayOf(-6.0, -5.0, +6.0, 1.0, 0.0),
		doubleArrayOf(+6.0, -5.0, +6.0, 0.0, 0.0),
		doubleArrayOf(+6.0, -5.0, -6.0, 0.0, 1.0)
	)
	override val indices = intArrayOf(
		0, 1, 2,
		0, 2, 3
	)
}