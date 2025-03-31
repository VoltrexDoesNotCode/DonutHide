package space.hugster.hide

import me.neznamy.tab.api.TabAPI
import org.bukkit.plugin.java.JavaPlugin
import revxrsal.commands.Lamp
import revxrsal.commands.bukkit.BukkitLamp
import revxrsal.commands.bukkit.actor.BukkitCommandActor
import space.hugster.hide.command.HideCommand
import space.hugster.hide.listener.HideListener

class DonutHide : JavaPlugin()
{
    companion object
    {
        lateinit var INSTANCE: DonutHide
    }

    private val lamp: Lamp<BukkitCommandActor> = BukkitLamp
        .builder(this).build()
    val tabAPI: TabAPI by lazy { TabAPI.getInstance() }

    override fun onEnable()
    {
        INSTANCE = this

        lamp.register(HideCommand())
        server.pluginManager.registerEvents(HideListener(), this)
    }
}