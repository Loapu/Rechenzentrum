/*
 * Copyright (c) Benjamin Selig, 2024, licensed under the EUPL
 */

package dev.loapu.rechenzentrum.paper;

import org.bukkit.plugin.java.JavaPlugin;

public class RechenzentrumPlugin extends JavaPlugin
{
	@Override
	public void onEnable()
	{
		getLogger().info("RechenzentrumPlugin enabled.");
	}
	
	@Override
	public void onDisable()
	{
		getLogger().info("RechenzentrumPlugin disabled.");
	}
}
