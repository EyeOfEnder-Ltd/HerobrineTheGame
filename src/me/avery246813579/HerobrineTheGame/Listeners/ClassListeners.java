package me.avery246813579.HerobrineTheGame.Listeners;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class ClassListeners implements Listener{

	private HerobrineTheGame plugin;
	
	public ClassListeners (HerobrineTheGame plugin){
		this.plugin = plugin;
	}
	
	@EventHandler
	public void onSignChange (SignChangeEvent event){
		if(event.getPlayer().hasPermission(plugin.getPerms().admin) || event.getPlayer().hasPermission(plugin.getPerms().createSigns)){
			if(event.getLine(0).equalsIgnoreCase("[Herobrine]")){
				//Signs Types
				
				/** Herobrine Signs **/
				if(event.getLine(0).equalsIgnoreCase("Herobrine")){
					event.setLine(0, ChatColor.GREEN + "Herobrine");
					event.setLine(1, ChatColor.WHITE + "Right Click");
					event.setLine(2, ChatColor.WHITE + "To Become");
					event.setLine(3, ChatColor.AQUA + "Herobrine");
				}
			}
		}
	}
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		/** Add Permissions **/
			if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				Block b = event.getClickedBlock();
				if (b.getType() == Material.WALL_SIGN || b.getType() == Material.SIGN_POST) {
					Sign sign = (Sign) b.getState();
					
					if(sign.getLine(0).equalsIgnoreCase(ChatColor.GREEN + "Herobrine")){
						/** Herobrine Signs **/
						if(sign.getLine(3).equalsIgnoreCase(ChatColor.AQUA + "Herobrine")){
							
						}
					}
				}
			}
	}
}
