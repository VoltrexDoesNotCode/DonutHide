package space.hugster.hide.command

import org.bukkit.entity.Player
import revxrsal.commands.annotation.Command
import revxrsal.commands.bukkit.annotation.CommandPermission
import space.hugster.hide.DonutHide
import space.hugster.hide.util.CC.translate

class HideCommand
{
    private val plugin by lazy { DonutHide.INSTANCE }

    @Command("hide")
    @CommandPermission("hide.use")
    fun onDefault(player: Player)
    {
        plugin.tabAPI.getPlayer(player.uniqueId)?.let { tabPlayer ->
            val currentPrefix = plugin.tabAPI.nameTagManager
                ?.getCustomPrefix(tabPlayer)
            val (newPrefix, message) = if (currentPrefix == "&k") {
                null to "&7Your nametag is now visible again."
            } else {
                "&k" to "&7You scrambled your nametag."
            }
            plugin.tabAPI.nameTagManager?.setPrefix(tabPlayer, newPrefix)
            player.sendMessage(translate(message))
        } ?: player.sendMessage(translate("&7TAB player data not found."))
    }

    @Command("hide all")
    @CommandPermission("hide.all")
    fun onAll(player: Player)
    {
        plugin.server.onlinePlayers.forEach { online ->
            plugin.tabAPI.getPlayer(online.uniqueId)?.let { tabPlayer ->
                val currentPrefix = plugin.tabAPI.nameTagManager
                    ?.getCustomPrefix(tabPlayer)
                val newPrefix = if (currentPrefix == "&k") null else "&k"
                plugin.tabAPI.nameTagManager?.setPrefix(tabPlayer, newPrefix)
            }
        }
        player.sendMessage(translate("&7Toggled nametag visibility for all players."))
    }
}