package me.avery246813579.HerobrineTheGame.Util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class FileCreator {

private HerobrineTheGame plugin;
	
	public FileCreator(HerobrineTheGame lh){
		this.plugin = lh;
	}
	
	private FileConfiguration player = null;
	private File playerFile = null;
	
	public void configSave(){
		plugin.getConfig().options().copyDefaults(true);
		getArena().options().copyDefaults(true);
		getPlayer().options().copyDefaults(true);
		plugin.saveConfig();
		saveArena();
		savePlayer();
	}
	
	public void reloadPlayer() {
	    if (playerFile == null) {
	    playerFile = new File(plugin.getDataFolder(), "player.yml");
	    }
	    player = YamlConfiguration.loadConfiguration(playerFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = plugin.getResource("player.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        player.setDefaults(defConfig);
	    }
	}
	
	public FileConfiguration getPlayer() {
	    if (player == null) {
	        this.reloadPlayer();
	    }
	    return player;
	}
	
	public void savePlayer() {
	    if (player == null || playerFile == null) {
	    return;
	    }
	    try {
	        getPlayer().save(playerFile);
	    } catch (IOException ex) {
	        plugin.getLogger().log(Level.SEVERE, "Could not save config to " + playerFile, ex);
	    }
	}
	
	public void saveDefaultPlayer() {
	    if (playerFile == null) {
	        playerFile = new File(plugin.getDataFolder(), "player.yml");
	    }
	    if (!playerFile.exists()) {            
	         this.plugin.saveResource("player.yml", false);
	     }
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////// arena File ///////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	private FileConfiguration arena = null;
	private File arenaFile = null;
	
	public void reloadArena() {
	    if (arenaFile == null) {
	    	arenaFile = new File(plugin.getDataFolder(), "arena.yml");
	    }
	    arena = YamlConfiguration.loadConfiguration(arenaFile);
	 
	    // Look for defaults in the jar
	    InputStream defConfigStream = plugin.getResource("arena.yml");
	    if (defConfigStream != null) {
	        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
	        arena.setDefaults(defConfig);
	    }
	}
	
	public FileConfiguration getArena() {
	    if (arena == null) {
	        this.reloadArena();
	    }
	    return arena;
	}
	
	public void saveArena() {
	    if (arena == null || arenaFile == null) {
	    return;
	    }
	    try {
	        getArena().save(arenaFile);
	    } catch (IOException ex) {
	        plugin.getLogger().log(Level.SEVERE, "Could not save config to " + arenaFile, ex);
	    }
	}
	
	public void saveDefaultArena() {
	    if (arenaFile == null) {
	        arenaFile = new File(plugin.getDataFolder(), "arena.yml");
	    }
	    if (!arenaFile.exists()) {            
	         this.plugin.saveResource("arena.yml", false);
	     }
	}
}
