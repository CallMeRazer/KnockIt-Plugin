package de.luis.knockit.main;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import de.luis.knockit.commands.SetSpawnCommand;
import de.luis.knockit.listeners.PlayerListeners;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	
	public void onEnable() {
		plugin = this;
		
		Bukkit.getConsoleSender().sendMessage("§6KnockIt §8| §7Plugin wurde §ageladen§7!"); /// Start Message (In der Konsole)
		
		getCommand("setspawn").setExecutor(new SetSpawnCommand());
		
		PluginManager pM = Bukkit.getPluginManager();
		pM.registerEvents(new PlayerListeners(), this);
		
	}
	
	public void onDisable() {
		
		Bukkit.getConsoleSender().sendMessage("§6KnockIt §8| §7Plugin wurde §centladen§7!"); /// Stop Message (In der Konsole)
		
		
	}
	public static JavaPlugin getPlugin() {
		return plugin;
	}
	
	

}