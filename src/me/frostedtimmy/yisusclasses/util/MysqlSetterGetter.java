package me.frostedtimmy.yisusclasses.util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.frostedtimmy.yisusclasses.YisusClasses;

public class MysqlSetterGetter implements Listener {
	
	YisusClasses plugin = YisusClasses.getPlugin(YisusClasses.class);
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (!playerExists(player.getUniqueId())) {
			createPlayer(player.getUniqueId(), player);
		}
	}
	
	public boolean playerExists(UUID uuid) {
		try {
			PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table + " WHERE UUID=?");
			statement.setString(1, uuid.toString());
			
			ResultSet results = statement.executeQuery();
			if(results.next()) {
				plugin.getServer().broadcastMessage("Player Found");
				return true;
			}
			
			plugin.getServer().broadcastMessage("Player Not Found");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void createPlayer(final UUID uuid, Player player) {
		try {
			PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table + " WHERE UUID=?");
			statement.setString(1, uuid.toString());
			
			ResultSet results = statement.executeQuery();
			results.next();
			
			if (playerExists(uuid) != true) {
				PreparedStatement insert = plugin.getConnection().prepareStatement("INSERT INTO " + plugin.table + "(UUID,NAME,CLASSID) VALUE (?,?,?)");
				insert.setString(1, uuid.toString());
				insert.setString(2, player.getName());
				insert.setInt(3, 0);
				insert.executeUpdate();
				
				plugin.getServer().broadcastMessage("Player Inserted");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateClassid(UUID uuid, int classid) {
		try {
			PreparedStatement statement = plugin.getConnection().prepareStatement("UPDATE " + plugin.table + " SET CLASSID=? WHERE UUID=?");
			statement.setInt(1, classid);
			statement.setString(2, uuid.toString());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getClassid(UUID uuid) {
		try {
			PreparedStatement statement = plugin.getConnection().prepareStatement("SELECT * FROM " + plugin.table + " WHERE UUID=?");
			statement.setString(1, uuid.toString());
			
			ResultSet results = statement.executeQuery();
			results.next();
			int classId = results.getInt("CLASSID");
			return classId;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
}
