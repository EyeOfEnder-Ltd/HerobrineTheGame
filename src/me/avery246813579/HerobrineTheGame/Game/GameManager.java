package me.avery246813579.HerobrineTheGame.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;
import me.avery246813579.HerobrineTheGame.Arena.Arena;
import me.avery246813579.HerobrineTheGame.Arena.ArenaItems;
import me.avery246813579.HerobrineTheGame.Arena.ArenaPlayer;
import me.avery246813579.HerobrineTheGame.Timers.LobbyTimer;
import me.avery246813579.HerobrineTheGame.Timers.SpawnShardTimer;

public class GameManager {
	
	private HerobrineTheGame plugin;
	
	/** Timers **/
	private int lobby;
	private int lobbyTimer;
	private boolean canCountdown = false;
	
	/** Players **/
	private Player Herobrine = null;
	private Player shardCarrier  = null;
	private List<Player> scoreboardPlayer = new ArrayList<Player>();
	
	private int shardSpawnTimer = 25;
	public int getShardSpawnTimer() {
		return shardSpawnTimer;
	}

	public void setShardSpawnTimer(int shardSpawnTimer) {
		this.shardSpawnTimer = shardSpawnTimer;
	}

	private int shardCollected = 0;
	private int shardRotation = 1;
	private int shardSpawn;
	
	private boolean canRespawn = false;
	
	/** Player Lists **/
	private List<Player> arenaPlayers = new ArrayList<Player>();
	private List<ArenaPlayer> arenaPlayerStats = new ArrayList<ArenaPlayer>();
	
	/** Arena Statistics **/
	private boolean inLobby = true;
	
	/** Herobrine Ticket **/
	private boolean herobrineTicket = false;
	
	private Arena arena;
	
	private String name = null;
	
	private GameScoreboard gs;
	
	private int gameBoard;
	
	public GameManager (HerobrineTheGame plugin, Arena arena){
		this.plugin = plugin;
		this.setArena(arena);
		this.setName(arena.getArenaName());
	}
	
	public void joinArena(Player player){
		if(!arena.isDisabled()){
			if(arena.getEndLocation() != null && arena.getHerobrineSpawn() != null && arena.getLobbySpawn() != null && arena.getRegularSpawn() != null && arena.getShardSpawn1() != null && arena.getShardSpawn2() != null && arena.getShardSpawn3() != null){
				if(this.isInLobby()){
					if(plugin.getConfigHelper().getMaxPlayers() >= this.getArenaPlayers().size()){
						if(checkPlayerStatus(player)){
							if(plugin.getConfigHelper().isClearInventoryOnJoin()){
								addPlayer(player);
							}else{
								if(this.checkInventory(player) == true){
									addPlayer(player);
								}else{
									plugin.sendMessage(player, "You need to clear your inventory before joining.");
								}
							}
						}else{
							plugin.sendMessage(player, "Error joining arena. You are already in one. Do /HB Leave to leave.");
						}
					}else if(player.hasPermission(plugin.getPerms().vip) || player.hasPermission(plugin.getPerms().joinWhenFull)){
						if(checkPlayerStatus(player) == true){
							if(plugin.getConfigHelper().isClearInventoryOnJoin()){
								kickRandomPlayer();
								addPlayer(player);
							}
						}else{
							if(this.checkInventory(player) == true){
								
							}else{
								plugin.sendMessage(player, "You need to clear your inventory before joining.");
							}
						}
					}else{
						plugin.sendMessage(player, "Error joining arena. Arena is full.");
					}
				}else{
					plugin.sendMessage(player, "Error joining arena. Game in progress.");
				}
			}else{
				plugin.sendMessage(player, "Error joining arena. Arena is not finished.");
			}
		}else{
			player.sendMessage("Error joinging arena. Arena is disabled.");
		}
	}
	
	public void addPlayer(Player player){
		/** Adds the players to the lists **/
		ArenaPlayer ap = new ArenaPlayer(player, this, plugin);
		ap.saveInventory();
		ap.clearInventory();
		arenaPlayerStats.add(ap);
		arenaPlayers.add(player);
		
		/** Sets gamemode and Health **/
		
		this.clearPotionEffects(player);
		this.resetHealth(player);
		
		/** Checks if the player can get help book **/
		if(plugin.getConfigHelper().isHelpBook()){
			ArenaItems ai = new ArenaItems(plugin);
			ai.addHelpBook(player);
		}
		
		/** Checks if player can fly in spawn **/
		if(plugin.getConfigHelper().isFlyInLobby()){
			player.setAllowFlight(true);
			player.setFlySpeed(0.1F);
			player.setFlying(true);
		}else{
			player.setAllowFlight(false);
			player.setFlySpeed(0.1F);
			player.setFlying(false);	
		}
	
		/** Teleports the player to the lobby **/
		player.teleport(arena.getLobbySpawn());
		
		/** Players a scary sound **/
		player.playSound(player.getLocation(), Sound.AMBIENCE_CAVE, 5, 5);
		player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 10);
		
		/** Sends a message to the player **/
		tellArena(ChatColor.AQUA + player.getName() + ChatColor.BLUE + " has joined the game. (" + arenaPlayers.size()  + "/" + plugin.getConfigHelper().getMaxPlayers() + ")");

		/** Checks the lobby **/
		checkLobby();	
	}

	public void checkLobby() {
		if(arenaPlayers.size() >= plugin.getConfigHelper().getPlayersToStart()){
			if(!this.canCountdown){
				setLobby(plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new LobbyTimer(plugin, this), 20L, 20L));
			}
		}
	}
	
	public void startLobbyCountdown(){
		if(!this.isCanCountdown()){
			this.setCanCountdown(true);
			this.lobbyTimer = plugin.getConfigHelper().getLobbyTimer();
			this.lobby = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new LobbyTimer(plugin, this), 20L, 20L);
		}
	}
	

	public void preGameSetup() {
		pickHerobrine();
		spawnPlayers();
		setUpHerobrine();
		spawnMessage();
		this.setInLobby(false);
		this.shardSpawn = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new SpawnShardTimer(plugin, this), 20L, 20L);
	}
	
	public void pickHerobrine(){
		if(!this.isHerobrineTicket()){
			if(this.getArenaPlayers().size() > 0){		
				if(this.Herobrine == null){
					int number = new Random().nextInt(this.getArenaPlayers().size());
					Player player = this.arenaPlayers.get(number);
					
					this.Herobrine = player;
					
					this.tellArena(player.getName() + " is the Herobrine.");
				}
			}else{
				/** Kicks all the players. Not enough */
				for(Player player : this.getArenaPlayers()){
					this.leave(player, false);
					plugin.sendMessage(player, "Not enough players!");
				}
				
				/** Stops the arena **/
				this.stopArena();
			}
		}
	}
	
	public void spawnPlayers(){
		for(Player player : this.arenaPlayers){
			if(player == this.Herobrine){
				player.teleport(arena.getHerobrineSpawn());
			}else{
				player.teleport(arena.getRegularSpawn());
			}
		}
		
		/** Sends a Message to the Console **/
		plugin.sendConsole("All players have been spawned in " + arena.getArenaName());
	}
	
	public void setUpHerobrine() {
		Player player = this.Herobrine;
		
		if(player != null){
			player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 100000, 3), true);
			player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD, 1));
		}else{
			return;
		}
	}
	
	private void spawnMessage() {
		for(Player player : this.getArenaPlayers()){
			player.sendMessage(ChatColor.GOLD+ "=-=-=-=-= =-=-=-=-= =-=-=-=-= =-=-=-=-=");
			player.sendMessage("");
			player.sendMessage(ChatColor.BLUE + "Map: " + arena.getArenaName());
			
			List<String> players = new ArrayList<String>();
			
			for(Player playerr : this.getArenaPlayers()){
				String playerName = playerr.getName();
				players.add(playerName + ", ");
			}
			String Playerz= "";
			for(String s : players){
				Playerz = Playerz + s; 
			}
			
			player.sendMessage(ChatColor.BLUE + "Players: " + Playerz);
			player.sendMessage(ChatColor.BLUE + "Herobrine : " + this.getHerobrine().getName());
			player.sendMessage("");
			player.sendMessage(ChatColor.GOLD+ "=-=-=-=-= =-=-=-=-= =-=-=-=-= =-=-=-=-=");
		}
	}

	public void shardCollected(Player player){
		this.setShardCarrier(null);
		shardCollected++;
		shardRotation++;
		
		if(shardRotation == 4){
			shardRotation = 1;
		}
		
		this.setShardSpawnTimer(25);
		
		if(shardCollected != 3){
			this.shardSpawn = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new SpawnShardTimer(plugin, this), 20L, 20L);
			tellArena(shardCollected + "/3 Shards have been collected. You are getting Stronger.");
		}else{
			//Leave timers. 
			//Congrats
		}
	}

	public void stopArena(){
		/** Stops all the timers **/
		plugin.getServer().getScheduler().cancelTask(lobby);
		
		for(Player player : this.getArenaPlayers()){
			this.leave(player, false);
			plugin.sendMessage(player, "Arena has been stopped.");
		}
		
		plugin.getActiveArenas().remove(this);
	}
	
	public void clearPotionEffects(Player player)
	{
		for (PotionEffect effect : player.getActivePotionEffects())
		{
			player.removePotionEffect(effect.getType());
		}
	}
	
	public void resetHealth(Player player){
		player.setGameMode(GameMode.SURVIVAL);
		player.setHealth(20.0);
		player.setFoodLevel(20);
		player.setFireTicks(0);
		player.setExp(0);
	}
	
	public void kickRandomPlayer(){
		int number = new Random().nextInt(arenaPlayers.size());
		Player kickPlayer = arenaPlayers.get(number);
		this.leave(kickPlayer, false);
		plugin.sendMessage(kickPlayer, "You have been kicked to make space for a premium player.");
	}
	
	public void leave(Player player, boolean message){
		this.getArenaPlayerStats(player).returnInventory();
		this.arenaPlayers.remove(player);
		this.arenaPlayerStats.remove(this.getArenaPlayerStats(player));
		
		if(this.getArenaPlayers().size() == 0){
			this.stopArena();
		}
	}
	
	/** Checks if the player is in a arena **/
	public boolean checkPlayerStatus(Player player){
		for(GameManager gm : plugin.getActiveArenas()){
			if(gm.getArenaPlayers().contains(player)){
				return false;
			}
		}
		return true;
	}
	
	/** Checks if the player has Items In there inventory **/
	public boolean checkInventory(Player player){
		boolean isEmpty = true;
		
		for (ItemStack itemStack : player.getInventory().getContents()) {
			if(itemStack != null){
				isEmpty = false;
			}
		}
		
		if(isEmpty == true){
			return true;
		}else{
			return false;
		}
	}
	
	public ArenaPlayer getArenaPlayerStats(Player player){
		for(ArenaPlayer ap: arenaPlayerStats){
			if(ap.getPlayerName().equalsIgnoreCase(player.getName())){
				return ap;
			}
		}
		return null;
	}
	
	public void tellArena(String Message){
		for(Player player: arenaPlayers){
			plugin.sendMessage(player, Message);
		}
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public Player getHerobrine() {
		return Herobrine;
	}

	public void setHerobrine(Player herobrine) {
		Herobrine = herobrine;
	}

	public Player getShardCarrier() {
		return shardCarrier;
	}

	public void setShardCarrier(Player shardCarrier) {
		this.shardCarrier = shardCarrier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isInLobby() {
		return inLobby;
	}

	public void setInLobby(boolean inLobby) {
		this.inLobby = inLobby;
	}
	
	public List<Player> getArenaPlayers(){
		return this.arenaPlayers;
	}

	public boolean isCanCountdown() {
		return canCountdown;
	}

	public void setCanCountdown(boolean canCountdown) {
		this.canCountdown = canCountdown;
	}

	public boolean isHerobrineTicket() {
		return herobrineTicket;
	}

	public void setHerobrineTicket(boolean herobrineTicket) {
		this.herobrineTicket = herobrineTicket;
	}

	public int getLobbyTimer() {
		return lobbyTimer;
	}

	public void setLobbyTimer(int lobbyTimer) {
		this.lobbyTimer = lobbyTimer;
	}

	public int getLobby() {
		return lobby;
	}

	public void setLobby(int lobby) {
		this.lobby = lobby;
	}

	public GameScoreboard getGs() {
		return gs;
	}

	public void setGs(GameScoreboard gs) {
		this.gs = gs;
	}

	public int getGameBoard() {
		return gameBoard;
	}

	public void setGameBoard(int gameBoard) {
		this.gameBoard = gameBoard;
	}

	public int getShardRotation() {
		return shardRotation;
	}

	public void setShardRotation(int shardRotation) {
		this.shardRotation = shardRotation;
	}

	public int getShardCollected() {
		return shardCollected;
	}

	public void setShardCollected(int shardCollected) {
		this.shardCollected = shardCollected;
	}

	public int getShardSpawn() {
		return shardSpawn;
	}

	public void setShardSpawn(int shardSpawn) {
		this.shardSpawn = shardSpawn;
	}
	
	public List<Player> getScoreboardPlayer() {
		return scoreboardPlayer;
	}

	public void setScoreboardPlayer(List<Player> scoreboardPlayer) {
		this.scoreboardPlayer = scoreboardPlayer;
	}

	public boolean isCanRespawn() {
		return canRespawn;
	}

	public void setCanRespawn(boolean canRespawn) {
		this.canRespawn = canRespawn;
	}
}
