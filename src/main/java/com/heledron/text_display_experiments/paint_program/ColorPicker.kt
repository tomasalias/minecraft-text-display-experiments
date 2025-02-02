package com.heledron.text_display_experiments.paint_program

import com.heledron.text_display_experiments.utilities.hsv
import com.heledron.text_display_experiments.utilities.toHSV
import org.bukkit.Color

object ColorPicker {
    private var selectedPrivate: Color = Color.fromRGB(255, 0, 0)

    var selected: Color
        get() = selectedPrivate
        set(color) {
            selectedPrivate = color

            val (h,s,v) = color.toHSV()
            HuePicker.hue = h.toInt()
            SVPicker.sv = s to v
        }


    init {
        fun updateColor() {
            selectedPrivate = hsv(HuePicker.hue.toDouble(), SVPicker.sv.first, SVPicker.sv.second)
        }

        HuePicker.onPick = ::updateColor
        SVPicker.onPick = ::updateColor
    }
}