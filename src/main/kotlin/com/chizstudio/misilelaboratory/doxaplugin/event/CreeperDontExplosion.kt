package com.chizstudio.misilelaboratory.doxaplugin.event

import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.ExplosionPrimeEvent

class CreeperDontExplosion: Listener {
    @EventHandler
    fun notexplosioncreeper(e: ExplosionPrimeEvent) {
        if (e.entityType == EntityType.CREEPER) {
            e.isCancelled = true
        }
    }
}