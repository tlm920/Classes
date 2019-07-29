package me.frostedtimmy.yisusclasses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.frostedtimmy.yisusclasses.commands.ClassCommand;
import me.frostedtimmy.yisusclasses.events.AssassinSneak;
import me.frostedtimmy.yisusclasses.events.ClassInvClick;
import me.frostedtimmy.yisusclasses.events.MarksmanBow;
import me.frostedtimmy.yisusclasses.util.MysqlSetterGetter;
import net.md_5.bungee.api.ChatColor;

public class YisusClasses extends JavaPlugin {
	private Connection connection;
	public String host, database, username, password, table;
	public int port;
	
	@Override
	public void onEnable() {
		loadConfig();
		mysqlSetup();
		
		getCommand("class").setExecutor(new ClassCommand(this));
		
		this.getServer().getPluginManager().registerEvents(new MysqlSetterGetter(), this);
		this.getServer().getPluginManager().registerEvents(new MarksmanBow(), this);
		this.getServer().getPluginManager().registerEvents(new AssassinSneak(), this);
		new ClassInvClick(this);
	}
	
	public void loadConfig() {
		getConfig().options().copyDefaults(true);
		saveConfig();
	}
	
	public void mysqlSetup() {
		host = this.getConfig().getString("host");
		port = this.getConfig().getInt("port");
		database = this.getConfig().getString("database");
		username = this.getConfig().getString("username");
		password = this.getConfig().getString("password");
		table = this.getConfig().getString("table");
		
		try {
			
			synchronized (this) {
				if (getConnection() != null && !getConnection().isClosed()) {
					return;
				}
				
				Class.forName("com.mysql.jdbc.Driver");
				setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password));
				
				Bukkit.getConsoleSender().sendMessage("[YisusClasses] " + ChatColor.GOLD + "Database successfully connected.");
			}
			
			
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}


}
