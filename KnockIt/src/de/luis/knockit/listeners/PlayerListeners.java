package de.luis.knockit.listeners;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.luis.knockit.main.Main;

public class PlayerListeners implements Listener {
	
	@EventHandler
	public void onJoin (PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(null); /// Diese Nachricht kannst du festlegen wenn du möchtest. Nutze: e.setJoinMessage("Nachricht");
		FileConfiguration spawn = Main.getPlugin().getConfig();
		World world = Bukkit.getWorld(spawn.getString("Spawn.World"));
		double x = spawn.getDouble("Spawn.X");
		double y = spawn.getDouble("Spawn.Y");
		double z = spawn.getDouble("Spawn.Z");
		float yaw = (float) spawn.getDouble("Spawn.Yaw");
		float pitch = (float) spawn.getDouble("Spawn.Pitch");
		Location location = new Location(world, x, y, z, yaw, pitch);
		p.teleport(location);
		p.setGameMode(GameMode.SURVIVAL);
		ItemStack is1 = new ItemStack(Material.STICK); /// Das Item was man bekommt
		ItemMeta im1 = is1.getItemMeta();
		im1.setDisplayName("§6KnockbackStick"); /// Der Name vom Item
		im1.addEnchant(Enchantment.KNOCKBACK, 2, true); /// Das Enchantment vom Item
		is1.setItemMeta(im1);
		p.getInventory().setItem(0, is1);
		ItemStack is2 = new ItemStack(Material.MAGMA_CREAM); /// Das Item für das HUB Objekt
		ItemMeta im2 = is2.getItemMeta();
		im2.setDisplayName("§c» Hub"); /// Der Name vom HUB Objekt
		is2.setItemMeta(im2); 
		p.getInventory().setItem(8, is2);
		
		
	}
	
	@EventHandler
	public void onQuit (PlayerQuitEvent e) {
		e.setQuitMessage(null); /// Diese Nachricht kannst du festlegen wenn du möchtest. Nutze: e.setQuitMessage("Nachricht");
	}
	
	@EventHandler
	public void onHunger (FoodLevelChangeEvent e) {
		e.setCancelled(true); /// Spieler haben NIE Hunger
	}
	
	@EventHandler
	public void onBuild (BlockPlaceEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("knockit.build")) { /// Permission um zu bauen
		} else
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onDestroy (BlockBreakEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("knockit.build")) { /// Permission um zu bauen
		} else
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onDeath (PlayerDeathEvent e) {
		Player p = e.getEntity();
		Player k = e.getEntity().getKiller();
		e.setDeathMessage(null);
			FileConfiguration spawn = Main.getPlugin().getConfig();
			World world = Bukkit.getWorld(spawn.getString("Spawn.World"));
			double x = spawn.getDouble("Spawn.X");
			double y = spawn.getDouble("Spawn.Y");
			double z = spawn.getDouble("Spawn.Z");
			float yaw = (float) spawn.getDouble("Spawn.Yaw");
			float pitch = (float) spawn.getDouble("Spawn.Pitch");
			Location location = new Location(world, x, y, z, yaw, pitch);
			p.teleport(location);
			p.sendMessage("§e•§6● KnockIt §8| §7Du bist gestorben."); /// Death Message an Opfer
			k.sendMessage("§e•§6● KnockIt §8| §7Du hast §6" + p.getName() + "§7 getötet."); /// Death Message an Killer
		}
	
	@EventHandler
	public void onRespawn (PlayerRespawnEvent e) {
		Player p = e.getPlayer();
		FileConfiguration spawn = Main.getPlugin().getConfig();
		World world = Bukkit.getWorld(spawn.getString("Spawn.World"));
		double x = spawn.getDouble("Spawn.X");
		double y = spawn.getDouble("Spawn.Y");
		double z = spawn.getDouble("Spawn.Z");
		float yaw = (float) spawn.getDouble("Spawn.Yaw");
		float pitch = (float) spawn.getDouble("Spawn.Pitch");
		Location location = new Location(world, x, y, z, yaw, pitch);
		p.teleport(location);
		ItemStack is1 = new ItemStack(Material.STICK); /// Item Typ
		ItemMeta im1 = is1.getItemMeta();
		im1.addEnchant(Enchantment.KNOCKBACK, 2, true); /// Enchantment vom Item
		im1.setDisplayName("§6KnockbackStick"); /// Item Name
	}
	
	@EventHandler
	public void onDrop (PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		if (p.hasPermission("knockit.admin")) { /// Permission um Items zu droppen
		} else
			e.setCancelled(true);
	}
	
	@EventHandler
	public void onClick (PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) { ///
			if (e.hasItem()) {																				  /// Die Systematik für /hub
			if (e.getItem().getType() == Material.MAGMA_CREAM) {											  ///
				p.performCommand("hub");																	  ///
				}
			}
		}
	}
	
}