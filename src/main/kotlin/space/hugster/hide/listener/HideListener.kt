package space.hugster.hide.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerQuitEvent
import space.hugster.hide.DonutHide

class HideListener : Listener
{
    private val plugin by lazy { DonutHide.INSTANCE }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent)
    {
        val player = event.player

        plugin.tabAPI.getPlayer(player.uniqueId)?.let { tabPlayer ->
            val currentPrefix = plugin.tabAPI.nameTagManager
                ?.getCustomPrefix(tabPlayer)
            if (currentPrefix == "&k") plugin.tabAPI
                .nameTagManager?.setPrefix(tabPlayer, null)
        }
    }
}