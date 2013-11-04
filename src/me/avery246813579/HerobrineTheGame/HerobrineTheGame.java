package me.avery246813579.HerobrineTheGame;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.avery246813579.HerobrineTheGame.Arena.Arena;
import me.avery246813579.HerobrineTheGame.Arena.ArenaCreator;
import me.avery246813579.HerobrineTheGame.Game.GameManager;
import me.avery246813579.HerobrineTheGame.Listeners.ClassListeners;
import me.avery246813579.HerobrineTheGame.Listeners.GameListener;
import me.avery246813579.HerobrineTheGame.Util.ConfigurationHelper;
import me.avery246813579.HerobrineTheGame.Util.FileCreator;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class HerobrineTheGame extends JavaPlugin{

	private List<GameManager> activeArenas = new ArrayList<GameManager>();

	private Logger logger = Logger.getLogger("Minecraft");
	private FileCreator fc = new FileCreator(this);
	private ArenaCreator ac = new ArenaCreator(this);
	private ConfigurationHelper configHelper = new ConfigurationHelper(this);
	private Permissions perms = new Permissions(this);
	
	@Override
	public void onEnable() {
		FileCreator fc = new FileCreator(this);
		fc.configSave();
		
		/** Scoreboard Stuff **/
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new GameListener(this), this);
		pm.registerEvents(new ClassListeners(this), this);
		
		configHelper.loadConfigHelper();
	}
	
	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		super.onDisable();
	}
	
	//Sending a message to the console.
		public void sendConsole(String Message){
			this.logger.info("[Herobrine] " + Message);
		}
		
		public void debug(String DebugMessage){
			//if(this.ch.getDebugMode()){
				this.logger.info("[Herobrine-Debug] " + DebugMessage);
			//}
		}
		
		public void sendConsoleError(String error){
			this.logger.info("[Herobrine-Error] " + error);
		}
		
	//Sends a message to a player.
	public void sendMessage(Player player, String Message){
		player.sendMessage(ChatColor.BLUE + "[" + ChatColor.GOLD + "The Herobrine" + ChatColor.BLUE + "] " + ChatColor.GRAY + Message);
	}
		
	/** Sends permissions **/
	public void sendPermission(Player player){
		player.sendMessage(ChatColor.DARK_RED + "You do not have access to that command");
	}
		
	public String showBlockCoords(Location l) {
		return(ChatColor.LIGHT_PURPLE + "" + l.getBlockX() + ", " + l.getBlockY() + ", " + l.getBlockZ());
	}
		
	@Override
	public boolean onCommand(CommandSender sender, Command command,String CommandLabel, String[] args) {
		Player player = (Player) sender;
		if (CommandLabel.equalsIgnoreCase("Create")) {
			ac.createArena(player, args[0]);
		}
		// Checks if the arena is created in a array list then creates it if it is not already.
		if(CommandLabel.equalsIgnoreCase("HB")){
			if(args[0].equalsIgnoreCase("Set")){
				if(args[1].equalsIgnoreCase("ArenaWorld")){
					ac.setArenaWorld(player, args[2]);
				}else if(args[1].equalsIgnoreCase("HerobrineSpawn")){
					ac.setHerobrineSpawn(player, args[2]);
				}else if(args[1].equalsIgnoreCase("LobbySpawn")){
					ac.setLobby(player, args[2]);
				}else if(args[1].equalsIgnoreCase("RegularSpawn")){
					ac.setRegularSpawn(player, args[2]);
				}else if(args[1].equalsIgnoreCase("Shard1")){
					ac.setShardSpawn1(player, args[2]);
				}else if(args[1].equalsIgnoreCase("Shard2")){
					ac.setShardSpawn2(player, args[2]);
				}else if(args[1].equalsIgnoreCase("Shard3")){
					ac.setShardSpawn3(player, args[2]);
				}else if(args[1].equalsIgnoreCase("End")){
					ac.setEndLocation(player, args[2]);
				}
			}else if(args[0].equalsIgnoreCase("Join")){
				GameManager gm = this.getGame(args[1]);
				if(activeArenas.contains(gm)){
					gm.addPlayer(player);
				}else{
					if(this.getFc().getArena().contains(args[1])){
						Arena arena = new Arena(this, args[1]);
						GameManager gmn = new GameManager(this, arena);
						gmn.joinArena(player);
						activeArenas.add(gmn);
					}else{
						this.sendMessage(player, "Arena not there." + args[1]);
					}
				}
			}else if(args[0].equalsIgnoreCase("Leave")){
				this.getPlayersGame(player).leave(player, true);
			}else if(args[0].equalsIgnoreCase("Countdown")){
				this.getPlayersGame(player).startLobbyCountdown();
			}
		}
		return false;
	}
	
	public GameManager getGame (String arena){
		for (int i = 0; i < activeArenas.size(); i++)
		{
			
			GameManager gm = activeArenas.get(i);
			
			if(gm.getName().equalsIgnoreCase(arena)){
				return gm;
			}
		}
		return null;
	}
	
	public GameManager getPlayersGame (Player player){
		for(GameManager gm : activeArenas){
			if(gm.getArenaPlayers().contains(player)){
				return gm;
			}
		}
		return null;
	}

	/**
	 * @return the fc
	 */
	public FileCreator getFc() {
		return fc;
	}

	/**
	 * @param fc the fc to set
	 */
	public void setFc(FileCreator fc) {
		this.fc = fc;
	}

	public ConfigurationHelper getConfigHelper() {
		return configHelper;
	}

	public void setConfigHelper(ConfigurationHelper configHelper) {
		this.configHelper = configHelper;
	}
	
	public List<GameManager> getActiveArenas() {
		return activeArenas;
	}

	public void setActiveArenas(List<GameManager> activeArenas) {
		this.activeArenas = activeArenas;
	}

	public Permissions getPerms() {
		return perms;
	}

	public void setPerms(Permissions perms) {
		this.perms = perms;
	}
}
