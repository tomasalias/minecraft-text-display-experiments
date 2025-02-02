package com.heledron.text_display_experiments.utilities

import org.bukkit.Bukkit
import org.bukkit.entity.Entity

class EntityTag(
    private val tag: String
)  {
    fun getEntities() = allEntities().filter { it.scoreboardTags.contains(tag) }

    fun onTick(action: (Entity) -> Unit) {
        com.heledron.text_display_experiments.utilities.onTick {
            getEntities().forEach { action(it) }
        }
    }

    fun onInteract(action: (event: org.bukkit.event.player.PlayerInteractEntityEvent) -> Unit) {
        onInteractEntity { event ->
            if (!event.rightClicked.scoreboardTags.contains(tag)) return@onInteractEntity
            action(event)
        }
    }
}

fun allEntities() = Bukkit.getServer().worlds.flatMap { it.entities }