/*
 * Copyright (c) Benjamin Selig, 2024, licensed under the EUPL
 */

package dev.loapu.rechenzentrum.paper.commands;

import com.mojang.brigadier.Command;
import dev.loapu.rechenzentrum.paper.RechenzentrumPlugin;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.plugin.Plugin;

public class BaseCommand
{
	private final RechenzentrumPlugin plugin;
	
	public BaseCommand(RechenzentrumPlugin plugin)
	{
		this.plugin = plugin;
		LifecycleEventManager<Plugin> lifecycleEventManager = plugin.getLifecycleManager();
		lifecycleEventManager.registerEventHandler(LifecycleEvents.COMMANDS, this::register);
	}
	
	private void register(ReloadableRegistrarEvent<Commands> commandsReloadableRegistrarEvent)
	{
		final Commands commands = commandsReloadableRegistrarEvent.registrar();
		commands.register(
			Commands.literal("rechenzentrum")
				.executes(context -> {
					context.getSource().getSender().sendRichMessage("<red>Rechenzentrum</red> <gray>v" + plugin.getPluginMeta().getVersion());
					return Command.SINGLE_SUCCESS;
				})
				.then(Commands.literal("reload")
						  .requires(source -> source.getSender().hasPermission("rechenzentrum.reload"))
						  .executes(context -> {
							  plugin.reloadConfig();
							  context.getSource().getSender().sendRichMessage("<green>Config reloaded.");
							  return Command.SINGLE_SUCCESS;
						  })
				)
				.build(),
			"Base command for Rechenzentrum"
		);
		
		
	}
}
