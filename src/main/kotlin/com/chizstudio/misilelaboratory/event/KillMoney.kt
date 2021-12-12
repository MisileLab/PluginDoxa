package com.chizstudio.misilelaboratory.event

import io.github.monun.heartbeat.coroutines.HeartbeatScope
import io.github.monun.heartbeat.coroutines.delayTick
import kotlinx.coroutines.launch
import net.kyori.adventure.text.Component
import org.bukkit.Sound
import org.bukkit.attribute.Attribute
import org.bukkit.entity.EntityType
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDeathEvent
import org.bukkit.event.entity.EntitySpawnEvent
import org.bukkit.event.player.PlayerJoinEvent

class KillMoney: Listener {
    @EventHandler
    fun onJoin(e: PlayerJoinEvent) {
        setupscoreboard(e.player)
    }

    fun setupscoreboard(player: Player) {
        val scoreboard = player.scoreboard
        if (scoreboard.getObjective("money") == null) {
            scoreboard.registerNewObjective("money", "dummy", Component.text("money"))
        }
    }

    @EventHandler
    fun spawneliteentity(e: EntitySpawnEvent) {
        if (e.entity is LivingEntity) {
            val randomnumber = (0..100).random()
            if (randomnumber == 100) {
                val entityname = e.entity.name
                val entity = e.entity as LivingEntity
                entity.customName = "&4&l[ Elite ] &f$entityname"
                entity.isCustomNameVisible = true
                entity.registerAttribute(Attribute.GENERIC_MAX_HEALTH)
                var value = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH)!!.baseValue
                value += 40
                entity.health += 40
                HeartbeatScope().launch {
                    delayTick(5 * 60 * 20)
                    entity.health = (0).toDouble()
                }
            }
        }
    }

    @EventHandler
    fun deathentity(e: EntityDeathEvent) {
        if (e.entity.name.contains("Elite")) {
            val killer = e.entity.killer
            if (killer != null) {
                val scoreboard = killer.scoreboard
                setupscoreboard(killer)
                val objective = scoreboard.getObjective("money")!!
                var money = objective.getScore(killer.name).score
                money += 5000
                broadcast(e.entity.server, "&4&l[ 현상금 ] &f${killer.name}(이)가 엘리트 몬스터를 사냥하여 5,000원을 획득했습니다!")
            } else {
                broadcast(e.entity.server, "&4&l[ 현상금 ] &f엘리트 몬스터가 떠나갔습니다!")
            }
        } else if (e.entity.type == EntityType.ENDER_DRAGON) {
            val killer = e.entity.killer
            if (killer != null) {
                setupscoreboard(killer)
                var money = killer.scoreboard.getObjective("money")!!.getScore(killer.name).score
                money += 70000
                broadcast(e.entity.server, "&4&l[ 현상금 ] &f${killer.name}(이)가 엔더 드래곤을 사냥하여 70,000 원을 획득했습니다!")
                killer.world.playSound(killer.location, Sound.BLOCK_CHAIN_BREAK, 1F, 1F)
            }
        } else if (e.entity.type == EntityType.WITHER) {
            val killer = e.entity.killer
            if (killer != null) {
                setupscoreboard(killer)
                var money = killer.scoreboard.getObjective("money")!!.getScore(killer.name).score
                money += 87000
                broadcast(e.entity.server, "&4&l[ 현상금 ] &f${killer.name}}(이)가 위더를 사냥하여 87,000 원을 획득했습니다!")
                killer.world.playSound(killer.location, Sound.BLOCK_CHAIN_BREAK, 1F, 1F)
            }
        }
    }
}