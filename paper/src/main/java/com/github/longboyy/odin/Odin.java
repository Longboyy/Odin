package com.github.longboyy.odin;

import eu.endercentral.crazy_advancements.NameKey;
import eu.endercentral.crazy_advancements.advancement.Advancement;
import eu.endercentral.crazy_advancements.manager.AdvancementManager;
import vg.civcraft.mc.civmodcore.ACivMod;

public class Odin extends ACivMod {

	private static Odin INSTANCE;

	public static Odin instance(){
		return INSTANCE;
	}


	private OdinConfig config;
	private TutorialManager tutorialManager;

	@Override
	public void onEnable() {
		super.onEnable();
		INSTANCE = this;
		this.config = new OdinConfig(this);
		if(!this.config.parse()){
			this.disable();
			return;
		}
		this.tutorialManager = new TutorialManager(this);
	}

	@Override
	public void onDisable() {
		super.onDisable();
		this.tutorialManager.shutdown();
	}


}
