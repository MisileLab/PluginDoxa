package com.chizstudio.misilelaboratory.modules

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory

class ShopHandler {
    private fun createinventory(holder: Player, y: Int, title: String): Inventory {
        return Bukkit.createInventory(holder, (y * 8), Component.text(title))
    }
}