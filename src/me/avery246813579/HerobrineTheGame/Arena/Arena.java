package me.avery246813579.HerobrineTheGame.Arena;

import java.util.ArrayList;
import java.util.List;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;

import org.bukkit.Location;
import org.bukkit.World;

public class Arena {
	
	private HerobrineTheGame plugin;
	
	private boolean disabled = false;

	private String arenaName = null;
	private Location lobbySpawn = null;
	private Location herobrineSpawn = null;
	private Location regularSpawn = null;
	private Location shardSpawn1 = null;
	private Location shardSpawn2 = null;
	private Location shardSpawn3 = null;
	private Location endLocation = null;
	
	private World arenaWorld = null;
	
	public World getArenaWorld() {
		return arenaWorld;
	}

	public void setArenaWorld(World arenaWorld) {
		this.arenaWorld = arenaWorld;
	}

	private List<String> builders = new ArrayList<String>(); 
	
	public Arena(HerobrineTheGame plugin, String Arena){
		this.plugin = plugin;
		this.arenaName = Arena;
		
		loadArena(Arena);
	}

	public void loadArena(String ArenaUppercase){
		String Arena = ArenaUppercase.toLowerCase();
		World lobbyWorld = plugin.getServer().getWorld(plugin.getFc().getArena().getConfigurationSection(Arena).getString("lobbyWorld"));
	
		if(lobbyWorld != null){
			lobbySpawn = new Location(lobbyWorld, plugin.getFc().getArena().getConfigurationSection(Arena).getInt("lobbySpawn.x"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("lobbySpawn.y"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("lobbySpawn.z"));
		}else{
			plugin.sendConsole("Error loading arena " + this.getArenaName());
		}
		
		World arenaWorldd = plugin.getServer().getWorld(plugin.getFc().getArena().getConfigurationSection(Arena).getString("arenaWorld"));
		
		this.arenaWorld = arenaWorldd;
		
		if(arenaWorld != null){
			herobrineSpawn = new Location(arenaWorldd, plugin.getFc().getArena().getConfigurationSection(Arena).getInt("herobrineSpawn.x"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("herobrineSpawn.y"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("herobrineSpawn.z"));
			regularSpawn = new Location(arenaWorldd, plugin.getFc().getArena().getConfigurationSection(Arena).getInt("regularSpawn.x"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("regularSpawn.y"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("regularSpawn.z"));
			shardSpawn1 = new Location(arenaWorldd, plugin.getFc().getArena().getConfigurationSection(Arena).getInt("shardSpawn1.x"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("shardSpawn1.y"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("shardSpawn1.z"));
			shardSpawn2 = new Location(arenaWorldd, plugin.getFc().getArena().getConfigurationSection(Arena).getInt("shardSpawn2.x"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("shardSpawn2.y"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("shardSpawn2.z"));
			shardSpawn3 = new Location(arenaWorldd, plugin.getFc().getArena().getConfigurationSection(Arena).getInt("shardSpawn3.x"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("shardSpawn3.y"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("shardSpawn3.z"));
		}
		
		World endWorld = plugin.getServer().getWorld(plugin.getFc().getArena().getConfigurationSection(Arena).getString("endLocation.world"));

		if(endWorld != null){		
			endLocation = new Location(endWorld, plugin.getFc().getArena().getConfigurationSection(Arena).getInt("endLocation.x"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("endLocation.y"), plugin.getFc().getArena().getConfigurationSection(Arena).getInt("endLocation.z"));
		}
		
		if(plugin.getFc().getArena().getConfigurationSection(Arena).getBoolean("Disabled")){
			setDisabled(true);
		}

		plugin.debug("Loaded arena " + this.getArenaName());
	}
	
	/////////////////////////////////////////////////////////////////////////
	/////////////////////////// Getters and Setters ////////////////////////
	///////////////////////////////////////////////////////////////////////
	
	public Location getLobbySpawn() {
		return lobbySpawn;
	}

	public void setLobbySpawn(Location lobbySpawn) {
		this.lobbySpawn = lobbySpawn;
	}

	public Location getHerobrineSpawn() {
		return herobrineSpawn;
	}

	public void setHerobrineSpawn(Location herobrineSpawn) {
		this.herobrineSpawn = herobrineSpawn;
	}

	public Location getRegularSpawn() {
		return regularSpawn;
	}

	public void setRegularSpawn(Location regularSpawn) {
		this.regularSpawn = regularSpawn;
	}

	public Location getShardSpawn1() {
		return shardSpawn1;
	}

	public void setShardSpawn1(Location shardSpawn1) {
		this.shardSpawn1 = shardSpawn1;
	}

	public Location getShardSpawn2() {
		return shardSpawn2;
	}

	public void setShardSpawn2(Location shardSpawn2) {
		this.shardSpawn2 = shardSpawn2;
	}

	/**
	 * @return the shardSpawn3
	 */
	public Location getShardSpawn3() {
		return shardSpawn3;
	}

	/**
	 * @param shardSpawn3 the shardSpawn3 to set
	 */
	public void setShardSpawn3(Location shardSpawn3) {
		this.shardSpawn3 = shardSpawn3;
	}

	/**
	 * @return the endLocation
	 */
	public Location getEndLocation() {
		return endLocation;
	}

	/**
	 * @param endLocation the endLocation to set
	 */
	public void setEndLocation(Location endLocation) {
		this.endLocation = endLocation;
	}

	/**
	 * @return the arenaName
	 */
	public String getArenaName() {
		return arenaName;
	}

	/**
	 * @param arenaName the arenaName to set
	 */
	public void setArenaName(String arenaName) {
		this.arenaName = arenaName;
	}

	/**
	 * @return the disabled
	 */
	public boolean isDisabled() {
		return disabled;
	}

	/**
	 * @param disabled the disabled to set
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
