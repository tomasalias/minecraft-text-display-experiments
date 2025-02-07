package com.heledron.text_display_experiments

import com.heledron.text_display_experiments.bitmaps.setupBitmapDisplay
import com.heledron.text_display_experiments.paint_program.setupPaintProgram
import com.heledron.text_display_experiments.particle_system.setupParticleSystem
import com.heledron.text_display_experiments.point_detector_visualizer.setUpPointDetectorVisualizer
import com.heledron.text_display_experiments.utilities.*
import com.heledron.text_display_experiments.commands.UnixCommands
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.joml.*

val textBackgroundTransform: Matrix4f; get() = Matrix4f()
    .translate(-0.1f + .5f,-0.5f + .5f,0f)
    .scale(8.0f,4.0f,1f) //  + 0.003f  + 0.001f

@Suppress("unused")
class TextDisplayExperimentsPlugin : JavaPlugin() {
    override fun onDisable() {
        logger.info("Disabling Text Display Experiments plugin")
        closeCurrentPlugin()
    }

    override fun onEnable() {
        logger.info("Enabling Text Display Experiments plugin")

        currentPlugin = this

        setupBitmapDisplay()
        setupTextEntityUtilities()
        setupPaintProgram()
        setupCloak()
        setupParticleSystem()
        setupColoredCauldrons()
        setUpPointDetectorVisualizer()

        getCommand("items")?.setExecutor { sender, _, _, _ ->
            openCustomItemInventory(sender as? Player ?: run {
                sender.sendMessage("Only players can use this command")
                return@setExecutor true
            })
            true
        }

        getCommand("input")?.setExecutor { sender, _, _, args ->
            val player = sender as? Player ?: run {
                sender.sendMessage("Only players can use this command")
                return@setExecutor true
            }

            if (args.isEmpty()) {
                player.sendMessage("No command provided")
                return@setExecutor true
            }

            val command = args[0]
            val params = args.drop(1).toTypedArray()

            val result = UnixCommands.executeCommand(command, params)
            player.sendMessage(result)

            true
        }
    }
}
