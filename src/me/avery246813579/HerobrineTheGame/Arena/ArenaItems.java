package me.avery246813579.HerobrineTheGame.Arena;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import me.avery246813579.HerobrineTheGame.HerobrineTheGame;

public class ArenaItems {

	@SuppressWarnings("unused")
	private HerobrineTheGame plugin;
	
	public ArenaItems (HerobrineTheGame plugin){
		this.plugin = plugin;
	}
	
	public ItemStack helpBook(){
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bm = (BookMeta) book.getItemMeta();
		bm.setAuthor(ChatColor.DARK_RED + "Herobrine");
		bm.setTitle("-=-=- Herobrine -=-=-");
		bm.setPages(Arrays.asList(ChatColor.DARK_BLUE + "-=-=-=-=-=-=-=-=-" + ChatColor.GOLD + "        Hot Potato    " + ChatColor.DARK_BLUE + "-=-=-=-=-=-=-=-=-"));
		ItemMeta im = book.getItemMeta();
		im.addEnchant(Enchantment.KNOCKBACK, 1, true);
		book.setItemMeta(im);
		book.setItemMeta(bm);
		return book;
	}
	
	public void addHelpBook(Player player){
		Inventory pi = player.getInventory();
		
		/** Place holder Items **/
		ItemStack a = new ItemStack(Material.IRON_DOOR_BLOCK);
		ItemStack b = new ItemStack(Material.CACTUS);
		ItemStack c = new ItemStack(Material.GHAST_TEAR);
		ItemStack d = new ItemStack(Material.QUARTZ_STAIRS);
		ItemStack e = new ItemStack(Material.HOPPER_MINECART);
		ItemStack f = new ItemStack(Material.BEACON);
		ItemStack g = new ItemStack(Material.SADDLE);
		ItemStack h = new ItemStack(Material.WOOL);
		
		/** Adds the items to the inventory **/
		pi.addItem(a);
		pi.addItem(b);
		pi.addItem(c);
		pi.addItem(d);
		pi.addItem(e);
		pi.addItem(f);
		pi.addItem(g);
		pi.addItem(h);
		
		pi.addItem(this.helpBook());
		
		/** Removes Filler Items **/
		pi.remove(Material.IRON_DOOR_BLOCK);
		pi.remove(Material.CACTUS);
		pi.remove(Material.GHAST_TEAR);
		pi.remove(Material.QUARTZ_STAIRS);
		pi.remove(Material.HOPPER_MINECART);
		pi.remove(Material.BEACON);
		pi.remove(Material.SADDLE);
		pi.remove(Material.WOOL);
		
		
	}
	
	/** Shard **/
	
	public ItemStack shard(){
		ItemStack book = new ItemStack(Material.NETHER_STAR);
		ItemMeta im = book.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Shard");
		List<String> lore = new ArrayList<String>();
		lore.add(ChatColor.BLUE + "Bring this to the alter.");
		im.addEnchant(Enchantment.KNOCKBACK, 1, true);
		book.setItemMeta(im);
		return book;
	}
}
