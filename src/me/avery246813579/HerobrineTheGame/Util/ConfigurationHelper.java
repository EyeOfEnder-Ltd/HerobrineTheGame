package me.avery246813579.HerobrineTheGame.Util;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;

public class ConfigurationHelper {

	private HerobrineTheGame plugin;
	
	/** Game Timers **/
	private int LobbyTimer = 90;
	
	/** Game Info **/
	private boolean clearInventoryOnJoin = false;
	private boolean saveInventory = true;
	private boolean useScoreboard = true;
	
	private boolean helpBook = true;
	private int broadcastIntervals = 20;
	private boolean broadcastAllPlayers = true;
	
	/** Lobby Info **/
	private boolean flyInLobby = false;
	
	/** Player Info **/
	private int maxPlayers = 16;
	private int playersToStart = 8;
	
	public ConfigurationHelper (HerobrineTheGame plugin){
		this.plugin = plugin;
	}
	
	public void loadConfigHelper(){
		if(plugin.getConfig().contains("lobbyTime")){
			this.setLobbyTimer(plugin.getConfig().getInt("lobbyTime"));
		}
		if(plugin.getConfig().contains("clearInventoryOnJoin")){
			this.setClearInventoryOnJoin(plugin.getConfig().getBoolean("clearInventoryOnJoin"));
		}
		if(plugin.getConfig().contains("maxPlayers")){
			this.setMaxPlayers(plugin.getConfig().getInt("maxPlayers"));
		}
		if(plugin.getConfig().contains("saveInventory")){
			this.setSaveInventory(plugin.getConfig().getBoolean("saveInventory"));
		}
		if(plugin.getConfig().contains("helpBook")){
			this.setHelpBook(plugin.getConfig().getBoolean("helpBook"));
		}
		if(plugin.getConfig().contains("flyInLobby")){
			this.setFlyInLobby(plugin.getConfig().getBoolean("flyInLobby"));
		}
		if(plugin.getConfig().contains("playersToStart")){
			this.setPlayersToStart(plugin.getConfig().getInt("playersToStart"));
		}
		if(plugin.getConfig().contains("broadcastIntervals")){
			this.setBroadcastIntervals(plugin.getConfig().getInt("broadcastIntervals"));
		}
		if(plugin.getConfig().contains("broadcastAllPlayers")){
			this.setBroadcastAllPlayers(plugin.getConfig().getBoolean("broadcastAllPlayers"));
		}
	}

	public int getLobbyTimer() {
		return LobbyTimer;
	}

	public void setLobbyTimer(int lobbyTimer) {
		LobbyTimer = lobbyTimer;
	}

	public boolean isClearInventoryOnJoin() {
		return clearInventoryOnJoin;
	}

	public void setClearInventoryOnJoin(boolean clearInventoryOnJoin) {
		this.clearInventoryOnJoin = clearInventoryOnJoin;
	}

	public int getMaxPlayers() {
		return maxPlayers;
	}

	public void setMaxPlayers(int maxPlayers) {
		this.maxPlayers = maxPlayers;
	}

	public boolean isSaveInventory() {
		return saveInventory;
	}

	public void setSaveInventory(boolean saveInventory) {
		this.saveInventory = saveInventory;
	}

	public boolean isHelpBook() {
		return helpBook;
	}

	public void setHelpBook(boolean helpBook) {
		this.helpBook = helpBook;
	}

	public boolean isFlyInLobby() {
		return flyInLobby;
	}

	public void setFlyInLobby(boolean flyInLobby) {
		this.flyInLobby = flyInLobby;
	}

	public int getPlayersToStart() {
		return playersToStart;
	}

	public void setPlayersToStart(int playersToStart) {
		this.playersToStart = playersToStart;
	}

	public int getBroadcastIntervals() {
		return broadcastIntervals;
	}

	public void setBroadcastIntervals(int broadcastIntervals) {
		this.broadcastIntervals = broadcastIntervals;
	}

	public boolean isBroadcastAllPlayers() {
		return broadcastAllPlayers;
	}

	public void setBroadcastAllPlayers(boolean broadcastAllPlayers) {
		this.broadcastAllPlayers = broadcastAllPlayers;
	}

	public boolean isUseScoreboard() {
		return useScoreboard;
	}

	public void setUseScoreboard(boolean useScoreboard) {
		this.useScoreboard = useScoreboard;
	}
	
	
}
