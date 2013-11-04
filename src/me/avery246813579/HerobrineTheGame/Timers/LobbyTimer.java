package me.avery246813579.HerobrineTheGame.Timers;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;
import me.avery246813579.HerobrineTheGame.Game.GameManager;

public class LobbyTimer implements Runnable {

	private HerobrineTheGame plugin;
	
	private GameManager gm;
	
	public LobbyTimer (HerobrineTheGame plugin, GameManager gm){
		this.plugin = plugin;
		this.gm = gm;
	}

	@Override
	public void run() {
		if(gm.getLobbyTimer() != -1){
			
			int timer = gm.getLobbyTimer();
			
			if(gm.getLobbyTimer() != 0){
				/** Broadcast Messages **/
				if(gm.getLobbyTimer() > 10){
					if(gm.getLobbyTimer() % plugin.getConfigHelper().getBroadcastIntervals() == 0){	
						if(plugin.getConfigHelper().isBroadcastAllPlayers()){
							for(Player player : plugin.getServer().getOnlinePlayers()){
								if(!gm.getArenaPlayers().contains(player)){
									plugin.sendMessage(player, "Do /HB Join " + gm.getName() + " - To join the game of Herobrine.");
								}else{
									plugin.sendMessage(player, "The game starts in " + gm.getLobbyTimer() + " seconds.");
								}
							}
						}else{
							for(Player player: gm.getArenaPlayers()){
								plugin.sendMessage(player, "The game starts in " + gm.getLobbyTimer() + " seconds.");
							}
						}
					}
				}else{
					if(gm.getLobbyTimer() % 1 == 0){
						for(Player player: gm.getArenaPlayers()){
							plugin.sendMessage(player, "The game starts in " + gm.getLobbyTimer() + " seconds.");
						}			
					}
				}
				
				/** Players a sound **/
				for(Player player : gm.getArenaPlayers()){
					if(gm.getLobbyTimer() > 10){
						if(gm.getLobbyTimer() % plugin.getConfigHelper().getBroadcastIntervals() == 0){	
							player.playSound(player.getLocation(), Sound.CLICK, 10, 10);
						}
					}else{
						if(gm.getLobbyTimer() % 1 == 0){
							player.playSound(player.getLocation(), Sound.CLICK, 10, 10);
						}
					}
				}
				
				/** Sets the players xp bar **/
				for(Player player : gm.getArenaPlayers()){
					if(gm.isCanCountdown()){
						player.setLevel(gm.getLobbyTimer());
					}
				}
				
				timer--;
				gm.setLobbyTimer(timer);
			}else{
				gm.preGameSetup();
				timer--;
				gm.setLobbyTimer(timer);
				plugin.getServer().getScheduler().cancelTask(gm.getLobby());
			}
		}
	}	
}
