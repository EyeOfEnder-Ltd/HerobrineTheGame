package me.avery246813579.HerobrineTheGame.Arena;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;
import me.avery246813579.HerobrineTheGame.Game.GameManager;
      
public class ArenaPlayer {

	private HerobrineTheGame plugin;
	
	/** Player Stats **/
	private int kills;
	private int deaths;
	private int killStreak;
	
	/** Player Info **/
	private String playerName;
	private Player player;
	
	/** Extra Info **/
	private GameManager game;
	private Location spawnBack;
	
	/** Inventory **/
	private ItemStack[] inventoryContents;
	private ItemStack[] armorContents;

	private String kit;
	
	public ArenaPlayer (Player player, GameManager gameManager, HerobrineTheGame plugin ) {
		this.setPlayer(player);
		this.setSpawnBack(player.getLocation());
		this.setGame(gameManager);
		
		this.plugin = plugin;
		this.playerName = player.getName();
	}
	
	public void saveInventory(){
		if(plugin.getConfigHelper().isSaveInventory()){
			this.inventoryContents = player.getInventory().getContents();
			this.armorContents = player.getInventory().getArmorContents();
		}
	}
	
	public void returnInventory(){
		if(plugin.getConfigHelper().isSaveInventory()){
			player.getInventory().setContents(inventoryContents);
			player.getInventory().setArmorContents(armorContents);
		}
	}
	
	public void clearInventory(){
		player.getInventory().clear();
		player.getInventory().setHelmet(null);
		player.getInventory().setChestplate(null);
		player.getInventory().setLeggings(null);
		player.getInventory().setBoots(null);
	}
	
	public void giveKit(){
		
	}
	
	/////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////// Getters and Setters ///////////////////////////
	///////////////////////////////////////////////////////////////////////////////////

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Location getSpawnBack() {
		return spawnBack;
	}

	public void setSpawnBack(Location spawnBack) {
		this.spawnBack = spawnBack;
	}

	public int getKills() {
		return kills;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getKillStreak() {
		return killStreak;
	}

	public void setKillStreak(int killStreak) {
		this.killStreak = killStreak;
	}

	public GameManager getGame() {
		return game;
	}

	public void setGame(GameManager game) {
		this.game = game;
	}

	public String getKit() {
		return kit;
	}

	public void setKit(String kit) {
		this.kit = kit;
	}
}
