package com.chizstudio.misilelaboratory.doxaplugin

import com.chizstudio.misilelaboratory.doxaplugin.event.CreeperDontExplosion
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class DoxaPlugin: JavaPlugin() {
    override fun onEnable() {
        server.pluginManager.registerEvents(CreeperDontExplosion(), this)
    }
}