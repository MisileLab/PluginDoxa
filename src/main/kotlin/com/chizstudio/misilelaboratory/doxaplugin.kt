package com.chizstudio.misilelaboratory

import com.chizstudio.misilelaboratory.event.AnnouncementChat
import com.chizstudio.misilelaboratory.event.CreeperDontExplosion
import io.github.monun.heartbeat.coroutines.HeartbeatScope
import kotlinx.coroutines.launch
import org.bukkit.plugin.java.JavaPlugin

@Suppress("unused")
class DoxaPlugin: JavaPlugin() {
    override fun onEnable() {
        server.pluginManager.registerEvents(CreeperDontExplosion(), this)
        HeartbeatScope().launch {
            AnnouncementChat().announcementtimer(server)
        }
    }
}