package com.chizstudio.misilelaboratory.modules

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class ShopHandler(player: Player) {
    private var inventoryhandler = InventoryHandler(player, 1, "상점")
    fun setupguimain() {
        inventoryhandler.changeinventory(Material.CRAFTING_TABLE, 1, name="&f상호 블럭", lore=convertstring("&f상호작용이 가능한 블럭들의 조합법을 익힐 수 있습니다."))
        inventoryhandler.changeinventory(Material.DIAMOND_PICKAXE, 3, name="&f장비 및 도구", lore=convertstring("&f장비와 도구들의 조합법을 익힐 수 있습니다."))
        inventoryhandler.changeinventory(Material.REDSTONE, 5, name="&f장비 및 도구", lore=convertstring("&f장비와 도구들의 조합법을 익힐 수 있습니다."))
        inventoryhandler.changeinventory(Material.PAPER, 7, name="&f판매 및 구매", lore=convertstring("&f물건들을 판매하거나 구매할 수 있습니다."))
    }

    private fun convertstring(string: String): List<Component> {
        return listOf(Component.text(string))
    }
}

class InventoryHandler(holder: Player, y: Int, title: String) {
    private var selfinventory = Bukkit.createInventory(holder, (y * 8), Component.text(title))
    private var selfholder = holder
    private var selftitle = title
    private var selfy = y

    fun changey(y: Int, autoreload: Boolean = true) {
        selfy = y
        if (autoreload) {
            reload()
        }
    }

    @Suppress("unused")
    fun changeholder(holder: Player, autoreload: Boolean = true) {
        selfholder = holder
        if (autoreload) {
            reload()
        }
    }

    fun changetitle(title: String, autoreload: Boolean = true) {
        selftitle = title
        if (autoreload) {
            reload()
        }
    }

    fun reload() {
        selfinventory = Bukkit.createInventory(selfholder, (selfy * 8), Component.text(selftitle))
    }

    fun changeinventory(change: Material, index: Int, amount: Int = 1, name: String? = null, lore: List<Component>? = null) {
        val itemstack = ItemStack(change, amount)
        val itemmeta = itemstack.itemMeta
        if (name != null) {
            itemmeta.displayName(Component.text(name))
        }
        itemmeta.lore(lore)
        selfinventory.setItem(index, itemstack)
    }
}