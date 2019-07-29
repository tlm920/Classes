package me.frostedtimmy.yisusclasses.events;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffectType;

import me.frostedtimmy.yisusclasses.YisusClasses;
import me.frostedtimmy.yisusclasses.util.MysqlSetterGetter;

public class MarksmanBow implements Listener {
	YisusClasses plugin = YisusClasses.getPlugin(YisusClasses.class);
	MysqlSetterGetter sql = new MysqlSetterGetter();
	
	@EventHandler
	public void onBowShot(EntityDamageByEntityEvent e) {
		Entity damager = e.getDamager();
		
		if (damager instanceof Arrow) {
			final Arrow arrow = (Arrow) e.getDamager();
			
			if (arrow.getShooter() instanceof Player && e.getEntity() instanceof Player) {
				Player victim = (Player) e.getEntity();
				Player shooter = (Player) arrow.getShooter();
				
				if (sql.getClassid(shooter.getUniqueId()) == 2) {
					victim.addPotionEffect(PotionEffectType.HARM.createEffect(1, 1));
				}
			}
		}
	}
}
