/*
 * Copyright (c) Benjamin Selig, 2024, licensed under the EUPL
 */

package dev.loapu.rechenzentrum.paper;

import dev.loapu.rechenzentrum.paper.commands.BaseCommand;
import dev.loapu.rechenzentrum.paper.listeners.PlayerLinksSendListener;
import org.bukkit.plugin.java.JavaPlugin;

public class RechenzentrumPlugin extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		saveDefaultConfig();
		reloadConfig();
		new BaseCommand(this);
		new PlayerLinksSendListener(this);
		getLogger().info("RechenzentrumPlugin enabled.");
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info("RechenzentrumPlugin disabled.");
	}
}
