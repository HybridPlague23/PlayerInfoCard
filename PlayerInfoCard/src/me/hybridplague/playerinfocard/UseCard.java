package me.hybridplague.playerinfocard;

import java.text.SimpleDateFormat;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.persistence.PersistentDataContainer;


public class UseCard implements Listener {

	@EventHandler
	public void onUse(PlayerInteractEvent e) {
		if (!e.hasItem()) return;
		if (e.getItem().getType() != Material.NAME_TAG) return;
		if (!e.getItem().hasItemMeta()) return;
		if (!e.getItem().getItemMeta().getDisplayName().contains("Information Card")) return;
		
		Player p = e.getPlayer();
		PersistentDataContainer container = e.getItem().getItemMeta().getPersistentDataContainer();
		
		NamespacedKey key = new NamespacedKey(PlayerInfoCard.getPlugin(PlayerInfoCard.class), "infocard");
		if (container.has(key,  new InformationDataType())) {
			Information info = container.get(key, new InformationDataType());
			p.sendMessage(ChatColor.GREEN + "Username: " + info.getName());
			p.sendMessage(ChatColor.GREEN + "UUID: " + info.getUuid());
			SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss");
			p.sendMessage(ChatColor.GREEN + "Date Issued: " + sd.format(info.getIssuedDate()));
			p.sendMessage(ChatColor.GREEN + "Date Joined: " + sd.format(info.getJoinDate()));
			p.sendMessage(ChatColor.GREEN + "Votes: " + info.getVotes());
			p.sendMessage(ChatColor.GREEN + "Balance: " + info.getBalance());
			if (Bukkit.getOfflinePlayer(info.getUuid()).isOnline()) {
				p.sendMessage(ChatColor.GREEN + "PlayTime: " + info.getPlayTime());
				p.sendMessage(ChatColor.GREEN + "Ping: " + Bukkit.getPlayer(info.getUuid()).getPing());
			}
			return;
		}
		return;
		
	}
	
}
