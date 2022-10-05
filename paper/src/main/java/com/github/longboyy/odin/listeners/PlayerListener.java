package com.github.longboyy.odin.listeners;

import com.github.longboyy.odin.TutorialManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerListener implements Listener {

	private final TutorialManager tutorialManager;

	public PlayerListener(TutorialManager tutorialManager){
		this.tutorialManager = tutorialManager;
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerJoin(PlayerJoinEvent event){
		this.tutorialManager.loadPlayerData(event.getPlayer());
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerLeave(PlayerQuitEvent event){
		this.tutorialManager.savePlayerData(event.getPlayer());
	}
}
