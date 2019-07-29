package me.frostedtimmy.yisusclasses.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import me.frostedtimmy.yisusclasses.YisusClasses;
import me.frostedtimmy.yisusclasses.util.MysqlSetterGetter;

public class AssassinSneak implements Listener {
	YisusClasses plugin = YisusClasses.getPlugin(YisusClasses.class);
	MysqlSetterGetter sql = new MysqlSetterGetter();
	
	@EventHandler 
	public void moveEvent(PlayerToggleSneakEvent e) {
		Player p = e.getPlayer();
		
		new BukkitRunnable() {
			@Override
			public void run() {
				if (sql.getClassid(p.getUniqueId()) == 3 && p.isSneaking() == true) {
					p.addPotionEffect(PotionEffectType.INVISIBILITY.createEffect(10, 1));
				}
			}
		}.runTaskTimer(plugin, 0L, 10L);
	}
}