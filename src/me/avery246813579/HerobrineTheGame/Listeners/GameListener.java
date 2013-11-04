package me.avery246813579.HerobrineTheGame.Listeners;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;
import me.avery246813579.HerobrineTheGame.Arena.ArenaItems;
import me.avery246813579.HerobrineTheGame.Game.GameManager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class GameListener implements Listener{

	private HerobrineTheGame plugin;
	
	private ArenaItems ai = new ArenaItems(plugin);
	
	public GameListener(HerobrineTheGame plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onPlayerPickupItem(PlayerPickupItemEvent event){
		if(plugin.getPlayersGame(event.getPlayer()) != null){
			if(event.getItem().getItemStack().getType() == Material.NETHER_STAR){
				GameManager gm = plugin.getPlayersGame(event.getPlayer());
				if(gm.isInLobby() == false){
					if(gm.getHerobrine() != event.getPlayer()){
						gm.setShardCarrier(event.getPlayer());
						gm.tellArena(ChatColor.BLUE + event.getPlayer().getName() + ChatColor.GRAY + " has picked up the shard.");
					}
				}
			}else{
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event){
		if(plugin.getPlayersGame(event.getPlayer()) != null){
			if(event.getPlayer().getItemInHand().getType() == Material.NETHER_STAR){
				if(event.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE){
					GameManager gm = plugin.getPlayersGame(event.getPlayer());
					gm.shardCollected(event.getPlayer());
				}
			}
		}
	}
}
