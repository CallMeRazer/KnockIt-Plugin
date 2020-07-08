package de.luis.knockit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import de.luis.knockit.main.Main;

public class SetSpawnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if(sender instanceof Player) {
			Player player = (Player) sender;
			if (player.hasPermission("knockit.setspawn")) { /// Die Permission ist Beliebig Einstellbar
				if (args.length == 0) {
					
					FileConfiguration spawn = Main.getPlugin().getConfig();
					spawn.set("Spawn.World", player.getWorld().getName());
					spawn.set("Spawn.X", player.getLocation().getX());
					spawn.set("Spawn.Y", player.getLocation().getY());
					spawn.set("Spawn.Z", player.getLocation().getZ());
					spawn.set("Spawn.Yaw", player.getLocation().getYaw());
					spawn.set("Spawn.Pitch", player.getLocation().getPitch());
					Main.getPlugin().saveConfig();
					player.sendMessage("§6§lKnockIt §8| §7Du hast den Spawnpunkt gesetzt."); /// Diese Nachricht Kannst du ändern
					
				} else
					player.sendMessage("§6§lKnockIt §8| §7Falscher Befehl"); /// Diese Nachricht Kannst du ändern
				
			} else
				player.sendMessage("§6§lKnockIt §8| §7Fehlende Rechte."); /// Diese Nachricht Kannst du ändern
		}
		
		return false;
	}

}