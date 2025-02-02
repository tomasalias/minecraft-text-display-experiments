package com.heledron.text_display_experiments

import com.heledron.text_display_experiments.utilities.*
import com.heledron.text_display_experiments.utilities.rendering.RenderEntity
import com.heledron.text_display_experiments.utilities.rendering.RenderEntityGroup
import com.heledron.text_display_experiments.utilities.rendering.interpolateTransform
import com.heledron.text_display_experiments.utilities.rendering.blockRenderEntity
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.entity.ArmorStand
import org.bukkit.util.Vector
import org.joml.Matrix4f
import org.joml.Quaterniond
import org.joml.Quaternionf

class Rocket(
    val world: World,
    val position: Vector,
) {
    val orientation = Quaterniond()

    val velocity = Vector(0, 0, 0)
    val angularVelocity = Vector(0, 0, 0)

    fun update() {
        position.add(velocity)
        orientation.rotateYXZ(angularVelocity.y, angularVelocity.x, angularVelocity.z)
    }

    fun addTorque(axis: Vector, torque: Double) {
        val torqueVector = axis.clone().multiply(torque)
        angularVelocity.add(torqueVector)
    }

    fun addLocalTorque(axis: Vector, torque: Double) {
        addTorque(orientation.transform(axis), torque)
    }
}

fun initRocket() {
    fun rocketToRenderEntity(rocket: Rocket): RenderEntityGroup {
        val group = RenderEntityGroup()
        val thickness = 1 / 16f
        val forwardMatrix = Matrix4f()
            .scale(thickness,thickness,2f)
            .translate(-.5f,-.5f,0f)

        val leftMatrix = Matrix4f()
            .scale(.3f, thickness,thickness)
            .translate(0f, -.5f,-.5f)

        val upMatrix = Matrix4f()
            .scale(thickness,.3f,thickness)
            .translate(-.5f,0f, -.5f)


        group.add("forward", blockRenderEntity(
            world = rocket.world,
            position = rocket.position,
            init = {
                it.block = Material.EMERALD_BLOCK.createBlockData()
                it.teleportDuration = 1
                it.interpolationDuration = 1
            },
            update = {
                it.interpolateTransform(Matrix4f().rotation(Quaternionf(rocket.orientation)).mul(forwardMatrix))
            }
        ))

        group.add("left", blockRenderEntity(
            world = rocket.world,
            position = rocket.position,
            init = {
                it.block = Material.EMERALD_BLOCK.createBlockData()
                it.teleportDuration = 1
                it.interpolationDuration = 1
            },
            update = {
                it.interpolateTransform(Matrix4f().rotation(Quaternionf(rocket.orientation)).mul(leftMatrix))
            }
        ))

        group.add("up", blockRenderEntity(
            world = rocket.world,
            position = rocket.position,
            init = {
                it.block = Material.REDSTONE_BLOCK.createBlockData()
                it.teleportDuration = 1
                it.interpolationDuration = 1
            },
            update = {
                it.interpolateTransform(Matrix4f().rotation(Quaternionf(rocket.orientation)).mul(upMatrix))
            }
        ))

        val forward = rocket.orientation.transform(FORWARD_VECTOR)
        val seatLocation = rocket.position.clone().add(forward.multiply(-3.0)).toLocation(rocket.world)

        group.add("seat", RenderEntity(
            clazz = ArmorStand::class.java,
            location = seatLocation,
            init = {
                it.setGravity(false)
                it.isInvisible = true
                it.isInvulnerable = true
                it.isSilent = true
                it.isCollidable = false
                it.isMarker = true
            },
            update = update@{
                val player = rocket.world.players.firstOrNull() ?: return@update
                it.addPassenger(player)

                // This is the only way to preserve passengers when teleporting.
                // Paper has a TeleportFlag, but it is not supported by Spigot.
                // https://jd.papermc.io/paper/1.21/io/papermc/paper/entity/TeleportFlag.EntityState.html
                runCommandSilently("execute as ${it.uniqueId} at @s run tp ${seatLocation.x} ${seatLocation.y} ${seatLocation.z}")
            }
        ))

        return group
    }

//    fun updatePlayerControls() {
//        val rocket = AppState.rocket ?: return
//        val player = rocket.world.players.firstOrNull() ?: return
//        val input = player.currentInput
//
//        if (input.isLeft)     rocket.addLocalTorque(FORWARD_VECTOR, 0.01)
//        if (input.isRight)    rocket.addLocalTorque(FORWARD_VECTOR, -0.01)
//        if (input.isForward)  rocket.addLocalTorque(LEFT_VECTOR, -0.01)
//        if (input.isBackward) rocket.addLocalTorque(LEFT_VECTOR, 0.01)
//    }
//
//    onTick {
//        updatePlayerControls()
//
//        val rocket = AppState.rocket
//
//        if (rocket != null) {
//            rocket.update()
//            AppState.renderer.render("rocket", rocketToRenderEntity(rocket))
//        }
//
//        AppState.renderer.flush()
//    }
//
//    onSpawnEntity { entity, _ ->
//        if (!entity.scoreboardTags.contains("rocket")) return@onSpawnEntity
//        AppState.rocket = Rocket(entity.world, entity.location.toVector())
//    }
//
//    onSpawnEntity { entity, _ ->
//        if (!entity.scoreboardTags.contains("remove_rocket")) return@onSpawnEntity
//        AppState.rocket = null
//    }
}