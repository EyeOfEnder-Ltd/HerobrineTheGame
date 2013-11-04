package me.avery246813579.HerobrineTheGame.Arena;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;
import me.avery246813579.HerobrineTheGame.Util.FileCreator;

public class ArenaCreator {

	private HerobrineTheGame plugin;
	
	private String arenaName = null;
	private Location lobbySpawn = null;
	private Location herobrineSpawn = null;
	private Location regularSpawn = null;
	private Location shardSpawn1 = null;
	private Location shardSpawn2 = null;
	private Location shardSpawn3 = null;
	private Location endLocation = null;

	public ArenaCreator (HerobrineTheGame plugin){
		this.plugin = plugin;
	}
	
	public void createArena(Player player, String upperCaseName){ 
		String name = upperCaseName.toLowerCase();
	   if (plugin.getFc().getArena().contains(name)) {
		   plugin.sendMessage(player, "Arena is already created.");
		   return;
	   }

	   plugin.getFc().getArena().createSection(name);
	   plugin.getFc().getArena().getConfigurationSection(name).set("Disabled", false);
	   plugin.getFc().saveArena();
	   plugin.sendMessage(player, "Created arena " + name + ". Do /SetLobby to set the lobby spawn.");
	}
	
	public void setLobby(Player player, String upperCaseName){
		String name = upperCaseName.toLowerCase();
		if (plugin.getFc().getArena().contains(name)) {
			
			Location l = player.getLocation();
			
			plugin.getFc().getArena().getConfigurationSection(name).set("lobbyWorld", l.getWorld().getName());
			plugin.getFc().getArena().getConfigurationSection(name).set("lobbySpawn.x", l.getBlockX());
			plugin.getFc().getArena().getConfigurationSection(name).set("lobbySpawn.y", l.getBlockY());
			plugin.getFc().getArena().getConfigurationSection(name).set("lobbySpawn.z", l.getBlockZ());
			
			plugin.sendMessage(player, "You have updated " + name + "'s Lobby Spawn.");
			plugin.getFc().saveArena();

		}else{
			plugin.sendMessage(player, "Arena is not created.");
			   return;
		}
	}
	
	public void setArenaWorld(Player player, String upperCaseName){
		String name = upperCaseName.toLowerCase();
		if (plugin.getFc().getArena().contains(name)) {
			
			Location l = player.getLocation();
			
			plugin.getFc().getArena().getConfigurationSection(name).set("arenaWorld", l.getWorld().getName());
			
			plugin.sendMessage(player, "You have updated " + name + "'s Arenas world location.");
			plugin.getFc().saveArena();

		}else{
			plugin.sendMessage(player, "Arena is not created.");
			   return;
		}
	}
	
	public void setRegularSpawn(Player player, String upperCaseName){
		String name = upperCaseName.toLowerCase();
		if (plugin.getFc().getArena().contains(name)) {
			
			Location l = player.getLocation();
			
			if(l.getWorld().getName() == plugin.getFc().getArena().getConfigurationSection(name).getString("arenaWorld")){
				plugin.getFc().getArena().getConfigurationSection(name).set("regularSpawn.x", l.getBlockX());
				plugin.getFc().getArena().getConfigurationSection(name).set("regularSpawn.y", l.getBlockY());
				plugin.getFc().getArena().getConfigurationSection(name).set("regularSpawn.z", l.getBlockZ());				
				plugin.sendMessage(player, "You have updated " + name + "'s Regular Spawn Point.");
				plugin.getFc().saveArena();

			}else{
				if(plugin.getFc().getArena().getConfigurationSection(name).contains("arenaWorld")){
					plugin.sendMessage(player, "You have to be in the same world as you set the arena world.");
					return;
				}else{
					plugin.sendMessage(player, "You need to set the arena world by doing /HB Set ArenaWorld");
					return;
				}
			}
		}else{
			plugin.sendMessage(player, "Arena is not created.");
			   return;
		}
	}
	
	public void setHerobrineSpawn(Player player, String upperCaseName){
		String name = upperCaseName.toLowerCase();
		if (plugin.getFc().getArena().contains(name)) {
			
			Location l = player.getLocation();
			
			if(l.getWorld().getName() == plugin.getFc().getArena().getConfigurationSection(name).getString("arenaWorld")){
				plugin.getFc().getArena().getConfigurationSection(name).set("herobrineSpawn.x", l.getBlockX());
				plugin.getFc().getArena().getConfigurationSection(name).set("herobrineSpawn.y", l.getBlockY());
				plugin.getFc().getArena().getConfigurationSection(name).set("herobrineSpawn.z", l.getBlockZ());				
				plugin.sendMessage(player, "You have updated " + name + "'s Herobrine Spawn Point.");
				plugin.getFc().saveArena();

			}else{
				if(plugin.getFc().getArena().getConfigurationSection(name).contains("arenaWorld")){
					plugin.sendMessage(player, "You have to be in the same world as you set the arena world.");
					return;
				}else{
					plugin.sendMessage(player, "You need to set the arena world by doing /HB Set ArenaWorld");
					return;
				}
			}
		}else{
			plugin.sendMessage(player, "Arena is not created.");
			   return;
		}
	}
	
	public void setShardSpawn1(Player player, String upperCaseName){
		String name = upperCaseName.toLowerCase();
		if (plugin.getFc().getArena().contains(name)) {
			
			Location l = player.getLocation();
			
			if(l.getWorld().getName() == plugin.getFc().getArena().getConfigurationSection(name).getString("arenaWorld")){
				plugin.getFc().getArena().getConfigurationSection(name).set("shardSpawn1.x", l.getBlockX());
				plugin.getFc().getArena().getConfigurationSection(name).set("shardSpawn1.y", l.getBlockY());
				plugin.getFc().getArena().getConfigurationSection(name).set("shardSpawn1.z", l.getBlockZ());				
				plugin.sendMessage(player, "You have updated " + name + "'s Shard 1 Spawn Point.");
				plugin.getFc().saveArena();

			}else{
				if(plugin.getFc().getArena().getConfigurationSection(name).contains("arenaWorld")){
					plugin.sendMessage(player, "You have to be in the same world as you set the arena world.");
					return;
				}else{
					plugin.sendMessage(player, "You need to set the arena world by doing /HB Set ArenaWorld");
					return;
				}
			}
		}else{
			plugin.sendMessage(player, "Arena is not created.");
			   return;
		}
	}
	
	public void setShardSpawn2(Player player, String upperCaseName){
		String name = upperCaseName.toLowerCase();
		if (plugin.getFc().getArena().contains(name)) {
			
			Location l = player.getLocation();
			
			if(l.getWorld().getName() == plugin.getFc().getArena().getConfigurationSection(name).getString("arenaWorld")){
				plugin.getFc().getArena().getConfigurationSection(name).set("shardSpawn2.x", l.getBlockX());
				plugin.getFc().getArena().getConfigurationSection(name).set("shardSpawn2.y", l.getBlockY());
				plugin.getFc().getArena().getConfigurationSection(name).set("shardSpawn2.z", l.getBlockZ());				
				plugin.sendMessage(player, "You have updated " + name + "'s Shard 2 Spawn Point.");
				plugin.getFc().saveArena();

			}else{
				if(plugin.getFc().getArena().getConfigurationSection(name).contains("arenaWorld")){
					plugin.sendMessage(player, "You have to be in the same world as you set the arena world.");
					return;
				}else{
					plugin.sendMessage(player, "You need to set the arena world by doing /HB Set ArenaWorld");
					return;
				}
			}
		}else{
			plugin.sendMessage(player, "Arena is not created.");
			   return;
		}
	}
	
	public void setShardSpawn3(Player player, String upperCaseName){
		String name = upperCaseName.toLowerCase();
		if (plugin.getFc().getArena().contains(name)) {
			
			Location l = player.getLocation();
			
			if(l.getWorld().getName() == plugin.getFc().getArena().getConfigurationSection(name).getString("arenaWorld")){
				plugin.getFc().getArena().getConfigurationSection(name).set("shardSpawn3.x", l.getBlockX());
				plugin.getFc().getArena().getConfigurationSection(name).set("shardSpawn3.y", l.getBlockY());
				plugin.getFc().getArena().getConfigurationSection(name).set("shardSpawn3.z", l.getBlockZ());				
				plugin.sendMessage(player, "You have updated " + name + "'s Shard 3 Spawn Point.");
				plugin.getFc().saveArena();

			}else{
				if(plugin.getFc().getArena().getConfigurationSection(name).contains("arenaWorld")){
					plugin.sendMessage(player, "You have to be in the same world as you set the arena world.");
					return;
				}else{
					plugin.sendMessage(player, "You need to set the arena world by doing /HB Set ArenaWorld");
					return;
				}
			}
		}else{
			plugin.sendMessage(player, "Arena is not created.");
			   return;
		}
	}
	
	public void setEndLocation(Player player, String upperCaseName){
		String name = upperCaseName.toLowerCase();
		if (plugin.getFc().getArena().contains(name)) {
			
			Location l = player.getLocation();
			
			plugin.getFc().getArena().getConfigurationSection(name).set("endLocation.world", l.getWorld().getName());
			plugin.getFc().getArena().getConfigurationSection(name).set("endLocation.x", l.getBlockX());
			plugin.getFc().getArena().getConfigurationSection(name).set("endLocation.y", l.getBlockY());
			plugin.getFc().getArena().getConfigurationSection(name).set("endLocation.z", l.getBlockZ());	
			
			plugin.sendMessage(player, "You have updated " + name + "'s End location.");
			plugin.getFc().saveArena();

		}else{
			plugin.sendMessage(player, "Arena is not created.");
			   return;
		}
	}
}
