package com.github.longboyy.odin;

import com.github.longboyy.odin.listeners.PlayerListener;
import eu.endercentral.crazy_advancements.JSONMessage;
import eu.endercentral.crazy_advancements.NameKey;
import eu.endercentral.crazy_advancements.advancement.Advancement;
import eu.endercentral.crazy_advancements.advancement.AdvancementDisplay;
import eu.endercentral.crazy_advancements.advancement.AdvancementFlag;
import eu.endercentral.crazy_advancements.advancement.AdvancementVisibility;
import eu.endercentral.crazy_advancements.advancement.progress.GenericResult;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.Objects;

public class TutorialManager {

	private static final String NAMESPACE = "odin";

	private final Odin plugin;
	private final AdvancementManager advancementManager;
	private final Advancement rootAdvancement;

	public TutorialManager(Odin odin){
		this.plugin = odin;
		this.advancementManager = new AdvancementManager(new NameKey(NAMESPACE, "tutorial"));
		AdvancementDisplay rootDisplay = new AdvancementDisplay(Material.BOOK,
				"Welcome to Civ!",
				"What have I done...",
				AdvancementDisplay.AdvancementFrame.TASK,
				AdvancementVisibility.ALWAYS
		);
		this.rootAdvancement = this.registerAdvancement("tutorial_root", rootDisplay);

		this.plugin.registerListener(new PlayerListener(this));
	}

	public boolean grantAdvancement(String id, Player player){
		Advancement advancement = this.advancementManager.getAdvancement(new NameKey(NAMESPACE, id));
		if(advancement == null){
			return false;
		}
		GenericResult result = this.advancementManager.grantAdvancement(player, advancement);
		return result == GenericResult.CHANGED;
	}

	public boolean revokeAdvancement(String id, Player player){
		Advancement advancement = this.advancementManager.getAdvancement(new NameKey(NAMESPACE, id));
		if(advancement == null){
			return false;
		}
		GenericResult result = this.advancementManager.revokeAdvancement(player, advancement);
		return result == GenericResult.CHANGED;
	}

	public Advancement registerAdvancement(String id, AdvancementDisplay display, AdvancementFlag... flags){
		NameKey key = new NameKey(NAMESPACE, id);
		Advancement advancement = new Advancement(this.rootAdvancement, key, display, flags);
		this.advancementManager.addAdvancement(advancement);
		return advancement;
	}

	public void loadPlayerData(Player player){
		if(!this.advancementManager.getPlayers().contains(player)){
			this.advancementManager.addPlayer(player);
		}

		this.advancementManager.loadProgress(player);
	}

	public void savePlayerData(Player player){
		this.advancementManager.saveProgress(player);
	}

	public void shutdown(){
		for(Player player : this.advancementManager.getPlayers()){
			this.savePlayerData(player);
		}
	}

}
