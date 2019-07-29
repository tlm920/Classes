package me.frostedtimmy.yisusclasses.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;

import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;

import me.frostedtimmy.yisusclasses.util.MysqlSetterGetter;

public class EnchanterItemsHeld implements Listener {
	MysqlSetterGetter sql = new MysqlSetterGetter();
	
	@EventHandler
	public void itemHeldEvent(PlayerItemHeldEvent e) {
		Player p = e.getPlayer();
		MPlayer mplayer = MPlayer.get(p);
		Faction faction = mplayer.getFaction();
		
		if (sql.getClassid(p.getUniqueId()) == 4 && p.getInventory().getItemInMainHand().getType() == Material.GOLD_HOE) {

		}
		
	}
	
}
