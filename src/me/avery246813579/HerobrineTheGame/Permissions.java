package me.avery246813579.HerobrineTheGame;

import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;

public class Permissions {
	
	private HerobrineTheGame plugin;
	
	public Permissions(HerobrineTheGame plugin){
		this.plugin = plugin;
	}

	/** List Group Permissions **/
	public Permission user = new Permission("herobrineTheGame.user");
	public Permission vip = new Permission("herobrineTheGame.vip");
	public Permission admin = new Permission("herobrineTheGame.admin");

	/** List of Single Permissions **/
	public Permission joinWhenFull = new Permission("herobrineTheGame.joinWhenFull");
	public Permission createSigns = new Permission("herobrineTheGame.createSigns");
	
	/** Enables the Permissions **/
	
	public void enablePermissions() {
		PluginManager pm = plugin.getServer().getPluginManager();
		
		/** Enables Groups **/
		pm.addPermission(user);
		pm.addPermission(vip);
		pm.addPermission(admin);		
		
		/** Enables Single Permissions **/
		pm.addPermission(joinWhenFull);
		pm.addPermission(createSigns);
	}	
	
	/** Disables the Permissions **/
	
	public void disablePermissions() {
		PluginManager pm = plugin.getServer().getPluginManager();
		
		/** Disables Groups **/
		pm.removePermission(user);
		pm.removePermission(vip);
		pm.removePermission(admin);
		
		/** Disable Single Permissions **/
		pm.removePermission(joinWhenFull);
		pm.removePermission(createSigns);
	}
}
