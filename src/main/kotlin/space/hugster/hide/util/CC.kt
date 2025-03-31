package space.hugster.hide.util

import org.bukkit.ChatColor
import java.util.regex.Pattern

object CC
{
    private val hexPattern = Pattern.compile("&#[A-Fa-f0-9]{6}")

    fun translate(text: String): String
    {
        var result = text

        val matcher = hexPattern.matcher(result)
        while (matcher.find()) {
            try {
                val color = matcher.group()
                val hexColor = color
                    .replace("&", "")
                    .replace("x", "#")

                val bungeeColor = net.md_5.bungee.api.ChatColor.of(hexColor)
                result = result.replace(color, bungeeColor.toString())
            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }
        }

        return ChatColor.translateAlternateColorCodes('&', result)
    }
}