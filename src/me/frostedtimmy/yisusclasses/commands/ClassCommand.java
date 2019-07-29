package me.frostedtimmy.yisusclasses.commands;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import me.frostedtimmy.yisusclasses.YisusClasses;
import me.frostedtimmy.yisusclasses.util.MysqlSetterGetter;
import net.md_5.bungee.api.ChatColor;

public class ClassCommand implements CommandExecutor {
	YisusClasses plugin;
	
	public ClassCommand(YisusClasses plugin){
		   this.plugin = plugin;
		}
	
	// The 9 slot gui.
	// 0 1 2 3 4 5 6 7 8
	// - - T D A E B - -
	public static void openGui(Player player) {
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.GREEN + "Classes");
		
		ItemStack tank = new ItemStack(Material.DIAMOND_CHESTPLATE);
		ItemMeta tankm = tank.getItemMeta();
		
		ItemStack dps = new ItemStack(Material.BOW);
		ItemMeta dpsm = dps.getItemMeta();
		
		ItemStack assassin = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta assassinm = assassin.getItemMeta();
		
		ItemStack ench = new ItemStack(Material.ENCHANTED_BOOK);
		ItemMeta enchm = ench.getItemMeta();
		
		ItemStack bruiser = new ItemStack(Material.SUGAR);
		ItemMeta bruiserm = bruiser.getItemMeta();
		
		tankm.setDisplayName(ChatColor.AQUA + "Tank");
		tank.setItemMeta(tankm);
		
		dpsm.setDisplayName(ChatColor.AQUA + "DPS");
		dps.setItemMeta(dpsm);
		
		assassinm.setDisplayName(ChatColor.AQUA + "Assassin");
		assassin.setItemMeta(assassinm);
		
		enchm.setDisplayName(ChatColor.AQUA + "Enchanter");
		ench.setItemMeta(enchm);
		
		bruiserm.setDisplayName(ChatColor.AQUA + "Bruiser");
		bruiser.setItemMeta(bruiserm);
		
		inv.setItem(2, tank);
		inv.setItem(3, dps);
		inv.setItem(4, assassin);
		inv.setItem(5, ench);
		inv.setItem(6, bruiser);
		
		player.openInventory(inv);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args) {
		Player p = (Player)sender;
		
		MysqlSetterGetter mysqlsettergetter = new MysqlSetterGetter();
		String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));
		
		if (!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "This command can only be executed by a player.");
		} else {
			if (cmd.getName().equalsIgnoreCase("class")) { 
				
				if (args.length == 0) {
						openGui(p);
						return true;
				}
				
				// Tank class
				// ID = 1
				if (args[0].equalsIgnoreCase("tank")) {
					mysqlsettergetter.updateClassid(p.getUniqueId(), 1);
					
					p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tank")));
					p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 2.0f);
					
					p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
					p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					
					p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(Integer.MAX_VALUE, 2), true);
					p.addPotionEffect(PotionEffectType.WEAKNESS.createEffect(Integer.MAX_VALUE, 1), true);
					
					p.setMaxHealth(36);
					p.setHealth(22);
					return true;
				}
				
				// Marksman class
				// ID = 2
				else if (args[0].equalsIgnoreCase("marksman")) {
					mysqlsettergetter.updateClassid(p.getUniqueId(), 2);
					
					p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("marksman")));
					p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 2.0f);
					
					p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
					p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					p.removePotionEffect(PotionEffectType.WEAKNESS);
					
					p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(Integer.MAX_VALUE, 0), true);
					
					p.setMaxHealth(20);
					p.setHealth(20);
					return true;
				}
				
				// Assassin class
				// ID = 3
				else if (args[0].equalsIgnoreCase("assassin")) {
					mysqlsettergetter.updateClassid(p.getUniqueId(), 3);
					
					p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("assassin")));
					p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 2.0f);
					
					p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
					p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					p.removePotionEffect(PotionEffectType.WEAKNESS);
					
					p.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(Integer.MAX_VALUE, 2), true);
					p.addPotionEffect(PotionEffectType.SPEED.createEffect(Integer.MAX_VALUE, 0), true);
					
					p.setMaxHealth(16);
					return true;
				} 
				
				// Enchanter class
				// ID = 4
				else if (args[0].equalsIgnoreCase("enchanter")) {
					mysqlsettergetter.updateClassid(p.getUniqueId(), 4);
					
					p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("enchanter")));
					p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 2.0f);
					
					p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
					p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					p.removePotionEffect(PotionEffectType.WEAKNESS);
					
					p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(Integer.MAX_VALUE, 0), true);
					p.addPotionEffect(PotionEffectType.INCREASE_DAMAGE.createEffect(Integer.MAX_VALUE, 0), true);
					
					p.getInventory().addItem(new ItemStack(Material.GOLD_HOE));
					p.getInventory().addItem(new ItemStack(Material.STONE_HOE));
					
					p.setMaxHealth(26);
					return true;
				}
				
				// Bruiser class
				// ID = 5
				else if (args[0].equalsIgnoreCase("bruiser")) {
					mysqlsettergetter.updateClassid(p.getUniqueId(), 5);
					
					p.sendMessage(prefix + ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("brusier")));
					p.playSound(p.getLocation(), Sound.UI_BUTTON_CLICK, 1.0f, 2.0f);
					
					p.removePotionEffect(PotionEffectType.DAMAGE_RESISTANCE);
					p.removePotionEffect(PotionEffectType.INCREASE_DAMAGE);
					
					p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(Integer.MAX_VALUE, 0), true);
					p.addPotionEffect(PotionEffectType.DAMAGE_RESISTANCE.createEffect(Integer.MAX_VALUE, 0), true);
					
					p.setMaxHealth(20);
					p.setHealth(20);
					
					return true;
				} 
				
				else if (args[0].equalsIgnoreCase("help")) {
					p.sendMessage("" +
							ChatColor.GOLD + ChatColor.BOLD +
							"<--------------------->\n" +
							ChatColor.RESET + ChatColor.GREEN +
							"Tank\n" + 
							"DPS\n" + 
							"Assassin\n" + 
							"Enchanter\n" + 
							"Bruiser\n" +
							ChatColor.GOLD + ChatColor.BOLD +
							"<--------------------->\n" +
							ChatColor.RESET + ChatColor.GREEN + 
							"\"/class {classname} info\" to see specs about the class.");
					return true;
				}
				
				// If player did not give valid argument, than send "invalid args" in red message to the player
				else {
					p.sendMessage(ChatColor.RED + "Invalid args.");
				}
			}
		}
		return true;
	}
	
}
