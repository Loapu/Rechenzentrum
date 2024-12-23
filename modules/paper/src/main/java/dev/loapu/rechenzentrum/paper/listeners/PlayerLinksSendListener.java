/*
 * Copyright (c) Benjamin Selig, 2024, licensed under the EUPL
 */

package dev.loapu.rechenzentrum.paper.listeners;

import dev.loapu.rechenzentrum.paper.RechenzentrumPlugin;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLinksSendEvent;

import java.net.URI;

public class PlayerLinksSendListener implements Listener
{
	private final RechenzentrumPlugin plugin;
	
	public PlayerLinksSendListener(RechenzentrumPlugin plugin)
	{
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onPlayerLinksSend(PlayerLinksSendEvent event)
	{
		var player = event.getPlayer();
		var mm = MiniMessage.miniMessage();
		var config = plugin.getConfig();
		for (var entry : config.getMapList("server-links"))
		{
			var name = entry.containsKey("name") ? entry.get("name").toString() : null;
			var url = entry.containsKey("url") ? entry.get("url").toString() : null;
			var permission = entry.containsKey("permission") ? entry.get("permission").toString() : null;
			if (name == null || url == null)
			{
				plugin.getLogger().warning("Invalid link entry: " + entry);
				continue;
			}
			if (permission != null && !player.hasPermission(permission)) continue;
			try
			{
				event.getLinks().addLink(mm.deserialize(name), URI.create(url));
			}
			catch (Exception e)
			{
				plugin.getLogger().warning("Failed to add link: " + name + " (" + url + ")");
			}
		}
	}
}
