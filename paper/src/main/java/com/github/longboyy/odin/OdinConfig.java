package com.github.longboyy.odin;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.Plugin;
import vg.civcraft.mc.civmodcore.config.ConfigParser;

public class OdinConfig extends ConfigParser {

	private final Odin plugin;

	public OdinConfig(Odin plugin) {
		super(plugin);
		this.plugin = plugin;
	}

	@Override
	protected boolean parseInternal(ConfigurationSection config) {
		return true;
	}
}
