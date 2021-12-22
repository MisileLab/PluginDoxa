package com.chizstudio.misilelaboratory.modules

import com.chizstudio.misilelaboratory.DoxaPlugin
import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack

class ShopHandler(player: Player) {
    private var inventoryhandler = InventoryHandler(player, 1, "상점")

    private fun getplugin(): DoxaPlugin {
        return DoxaPlugin().returnme()
    }

    fun setupKommand() {
        getplugin().kommand {
            register("상점") {
                executes {
                    setupguimain()
                }
            }
        }
    }

    private fun setupguimain() {
        inventoryhandler.changetitle("상점")
        inventoryhandler.changeinventory(Material.CRAFTING_TABLE, 1, name="&f상호 블럭", lore=convertstring("&f상호작용이 가능한 블럭들의 조합법을 익힐 수 있습니다."))
        inventoryhandler.changeinventory(Material.DIAMOND_PICKAXE, 3, name="&f장비 및 도구", lore=convertstring("&f장비와 도구들의 조합법을 익힐 수 있습니다."))
        inventoryhandler.changeinventory(Material.REDSTONE, 5, name="&f장비 및 도구", lore=convertstring("&f장비와 도구들의 조합법을 익힐 수 있습니다."))
        inventoryhandler.changeinventory(Material.PAPER, 7, name="&f판매 및 구매", lore=convertstring("&f물건들을 판매하거나 구매할 수 있습니다."))
        inventoryhandler.setfunctioninitem(1) { setupguiinteractionblock() }
        inventoryhandler.setfunctioninitem(2) { setupguitoolsandarmor() }
    }

    private fun setupguiinteractionblock() {
        inventoryhandler.changetitle("상호 블럭")
        inventoryhandler.changeinventory(Material.CRAFTING_TABLE, 0, name="&f제작대 조합법", lore=convertstring("&6구매가격 : &f5원"))
        inventoryhandler.changeinventory(Material.FURNACE, 1, name="&f화로 조합법", lore=convertstring("&6구매가격 : &f15원"))
        inventoryhandler.changeinventory(Material.CHEST, 2, name="&f상자 조합법", lore=convertstring("&6구매가격 : &f100원"))
        inventoryhandler.changeinventory(Material.ANVIL, 3, name="&f모루 조합법", lore=convertstring("&6구매가격 : &f3000원"))
        inventoryhandler.changeinventory(Material.SMITHING_TABLE, 4, name="&f대장장이 작업대 조합법", lore=convertstring("&6구매가격 : &f20000원"))
        inventoryhandler.changeinventory(Material.ENDER_CHEST, 5, name="&f엔더 상자 조합법", lore=convertstring("&6구매가격 : &f80000원"))
        inventoryhandler.changeinventory(Material.SHULKER_BOX, 6, name="&f셜커 상자 조합법", lore=convertstring("&6구매가격 : &f150000원"))
        inventoryhandler.changeinventory(Material.BEACON, 7, name="&f신호기 조합법", lore=convertstring("&6구매가격 : &f300000원"))
        inventoryhandler.changeinventory(Material.BARRIER, 8, name="&f뒤로가기")
        inventoryhandler.setfunctioninitem(8) { setupguimain() }
    }

    private fun setupguitoolsandarmor() {
        val scorenametag = inventoryhandler.returnholder().scoreboard.getObjective("nametagchange")?.getScore(inventoryhandler.returnholder().name)
        inventoryhandler.changetitle("장비 및 도구")
        inventoryhandler.changeinventory(Material.DIAMOND_PICKAXE, 0, name="&f도구")
        inventoryhandler.changeinventory(Material.DIAMOND_SWORD, 1, name="&f무기")
        inventoryhandler.changeinventory(Material.DIAMOND_CHESTPLATE, 2, name="&f갑옷")
        inventoryhandler.changeinventory(Material.NAME_TAG, 4, name="&f이름 변경 횟수 구매", lore=convertstring("&6현재 잔여 이름 변경 횟수는 $scorenametag" + "개 입니다."))
        inventoryhandler.changeinventory(Material.NAME_TAG, 5, name="&f이름표 구매", lore=convertstring("&6구매가격 : &f30,000원"))
        inventoryhandler.changeinventory(Material.BOOK, 6, name="&f책", lore=convertstring("&6구매가격 : &f1000원"))
        inventoryhandler.changeinventory(Material.STICK, 7, name="&f막대기 조합법", lore=convertstring("&6구매가격 : &f3원"))
        inventoryhandler.changeinventory(Material.BARRIER, 8, name="&f뒤로가기")
        inventoryhandler.setfunctioninitem(0) { setupguitools() }
        inventoryhandler.setfunctioninitem(4) { nametagscoreboardchange() }
        inventoryhandler.setfunctioninitem(5) { nametagget() }
        inventoryhandler.setfunctioninitem(8) { setupguimain() }
    }

    private fun nametagget() {
        val playerinventory = inventoryhandler.returnholder().inventory
        playerinventory.addItem(ItemStack(Material.NAME_TAG))
        nametagscoreboardchange()
    }

    private fun nametagscoreboardchange() {
        val player = inventoryhandler.returnholder()
        val scoreboard = player.scoreboard
        var nametagscoreboard = scoreboard.getObjective("nametagchange")?.getScore(player.name)?.score!!
        nametagscoreboard += 1
    }

    private fun setupguitools() {
        inventoryhandler.changetitle("도구")
        inventoryhandler.changeinventory(Material.WOODEN_PICKAXE, 0, name="&f나무 도구 조합법", lore=convertstring("&6구매가격 : &f15원"))
        inventoryhandler.changeinventory(Material.STONE_PICKAXE, 1, name="&f돌 도구 조합법", lore=convertstring("&6구매가격 : &f50원"))
        inventoryhandler.changeinventory(Material.GOLDEN_PICKAXE, 2, name="&f황금 도구 조합법", lore=convertstring("&6구매가격 : &f35원"))
        inventoryhandler.changeinventory(Material.IRON_PICKAXE, 3, name="&f철 도구 조합법", lore=convertstring("&6구매가격 : &f2700원"))
        inventoryhandler.changeinventory(Material.DIAMOND_PICKAXE, 4, name="&f다이아몬드 도구 조합법", lore=convertstring("&6구매가격 : &f12500원"))
        inventoryhandler.changeinventory(Material.SHEARS, 5, name="&f가위 조합법", lore=convertstring("&6구매가격 : &f700원"))
        inventoryhandler.changeinventory(Material.SHIELD, 6, name="&f방패 조합법", lore=convertstring("&6구매가격 : &f1500원"))
        inventoryhandler.changeinventory(Material.SADDLE, 7, name="&f안장", lore=convertstring("&6구매가격 : &f12500원"))
        inventoryhandler.changeinventory(Material.BARRIER, 8, name="&f뒤로가기")
        inventoryhandler.setfunctioninitem(8) { setupguitoolsandarmor() }
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
    private var selffunctions: MutableMap<Int, () -> Unit>? = null
    private var listeningInventory = ListeningInventory(this)

    init {
        val plugin = getPlugin()
        plugin.server.pluginManager.registerEvents(listeningInventory, plugin)
    }

    private fun getPlugin(): DoxaPlugin {
        return DoxaPlugin().returnme()
    }

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

    private fun reload() {
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

    fun setfunctioninitem(index: Int, function: () -> Unit) {
        if (selffunctions == null) {
            selffunctions = mutableMapOf(index to function)
        }
        else {
            selffunctions!![index] = function
        }
    }

    fun returnholder(): Player {
        return selfholder
    }

    fun returninventory(): Inventory {
        return selfinventory
    }

    fun returnfunctions(): MutableMap<Int, () -> Unit>? {
        return selffunctions
    }
}

class ListeningInventory(private val inventoryHandler: InventoryHandler): Listener {

    @EventHandler
    fun whenClickedInventory(e: InventoryClickEvent) {
        val functions = inventoryHandler.returnfunctions()
        val inventory = inventoryHandler.returninventory()
        if ((functions != null) || (e.inventory != inventory)) {
            val clickeditemslot = e.rawSlot
            if ((functions != null) && (functions[clickeditemslot] != null)) {
                val function = functions[clickeditemslot]
                function?.let { it() }
            }
        }
        e.isCancelled = true
    }
}