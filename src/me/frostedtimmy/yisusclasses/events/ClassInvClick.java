package me.frostedtimmy.yisusclasses.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import me.frostedtimmy.yisusclasses.YisusClasses;
import net.md_5.bungee.api.ChatColor;

public class ClassInvClick implements Listener {
	
	 YisusClasses plugin;
	
	public ClassInvClick(YisusClasses plugin) {
		this.plugin = plugin;
		
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		
		if (!ChatColor.stripColor(event.getInventory().getName()).equalsIgnoreCase("Classes")) {
				return;
		}
		
		Player p = (Player) event.getWhoClicked();
		event.setCancelled(true);
		
		if (event.getCurrentItem() == null || event.getCurrentItem().getType() == Material.AIR || !event.getCurrentItem().hasItemMeta()) {
			p.closeInventory();
			return;
		}
		
		if (event.getCurrentItem() != null) {
			switch (event.getCurrentItem().getType()) {
			case DIAMOND_CHESTPLATE:
				p.performCommand("class tank");
				p.closeInventory();
				break;
			case BOW:
				p.performCommand("class marksman");
				p.closeInventory();
				break;
			case DIAMOND_SWORD:
				p.performCommand("class assassin");
				p.closeInventory();
			break;
			case ENCHANTED_BOOK:
				p.performCommand("class enchanter");
				p.closeInventory();
				break;
			case SUGAR:
				p.performCommand("class enchanter");
				p.closeInventory();
				break;
			default:
				p.closeInventory();
				break;
		}
		
		}
	}
	
}
