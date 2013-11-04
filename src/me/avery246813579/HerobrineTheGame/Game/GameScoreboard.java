package me.avery246813579.HerobrineTheGame.Game;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;

public class GameScoreboard {
	
	private GameManager gm;
	
	private HerobrineTheGame plugin;
	
    public static Map<Player, Scoreboard> playerBoards = new HashMap<Player, Scoreboard>();
	
	public GameScoreboard (HerobrineTheGame plugin, GameManager gm){
		this.plugin = plugin;
		this.gm = gm;
	}
	
	public GameScoreboard (HerobrineTheGame plugin){
		this.plugin = plugin;
	}
	
	public void updateScoreboard(){
		if(plugin.getConfigHelper().isUseScoreboard()){
			for(Player player : gm.getScoreboardPlayer()){
				ScoreboardManager manager = Bukkit.getScoreboardManager();
				
				if(gm.isInLobby() == true){
					Scoreboard sb = manager.getNewScoreboard();
					Objective ob = sb.registerNewObjective("lobbyBoard", "dummy");
					
		            ob.setDisplaySlot(DisplaySlot.SIDEBAR);
					
					ob.setDisplayName(ChatColor.GOLD + "Herobrine");
					
					int needed = plugin.getConfigHelper().getPlayersToStart() - gm.getArenaPlayers().size(); 
					
					Score score1 = ob.getScore(Bukkit.getOfflinePlayer("Players:"));
					score1.setScore(gm.getArenaPlayers().size());
					
					if(needed > 0){
						if(!gm.isCanCountdown()){
							Score score = ob.getScore(Bukkit.getOfflinePlayer("Players Needed:"));
							score.setScore(needed);
						}	
					}
					
					if(gm.isCanCountdown()){
						if(gm.getLobbyTimer() > 0){
							Score score3 = ob.getScore(Bukkit.getOfflinePlayer("Starting in:"));
							score3.setScore(gm.getLobbyTimer());
						}
					}
					
					
					if(gm.isCanCountdown()){
						
					}
					
					player.setScoreboard(sb);
			
					
				}else{
					if(gm.getHerobrine() == player){
						
					}else{
						
					}
				}
			}
		}
	}
}
