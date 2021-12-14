package com.chizstudio.misilelaboratory.modules

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

/*
ShopHandler Class
*/

class InventoryHandler(holder: Player, y: Int, title: String) {
    private var selfinventory = Bukkit.createInventory(holder, (y * 8), Component.text(title))
    private var selfholder = holder
    private var selftitle = title
    private var selfy = y

    fun returninventory(): Inventory {
        return selfinventory
    }

    fun changeinventory(inventory: Inventory) {
        selfinventory = inventory
    }

    fun changey(y: Int, autoreload: Boolean = true) {
        selfy = y
        if (autoreload) {
            reload()
        }
    }

    fun changeholder(holder: Player, autoreload: Boolean = true) {
        selfholder = holder
        if (autoreload) {
            reload()
        }
    }

    fun changetitle(title: String, autoreload: Boolean) {
        selftitle = title
        if (autoreload) {
            reload()
        }
    }

    fun reload() {
        selfinventory = Bukkit.createInventory(selfholder, (selfy * 8), Component.text(selftitle))
    }

    fun changeinventorylist(change: List<Any>, y: Int) {
        val changelist = mutableListOf<Material>()
        for (i in change) {
            val i2: Material = if (i !is Material) {
                Material.AIR
            } else {
                i
            }
            changelist.add(i2)
        }
        changelist.forEachIndexed { index, material ->
            changeinventory(material, index)
        }
    }

    fun changeinventory(change: Material, index: Int, amount: Int=1) {
        selfinventory.setItem(index, ItemStack(change, amount))
    }
}