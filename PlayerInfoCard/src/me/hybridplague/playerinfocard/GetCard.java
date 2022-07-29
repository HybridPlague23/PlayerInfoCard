package me.hybridplague.playerinfocard;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.bencodez.votingplugin.VotingPluginHooks;

public class GetCard implements CommandExecutor {

	
	
	@SuppressWarnings("deprecation")
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player))
			return false;
		Player p = (Player) sender;
		
		if (!p.hasPermission("playerinfocard.getcard"))
			return false;
		
		if (p.getInventory().firstEmpty() == -1) {
			p.sendMessage("Cannot give card, inventory full!");
			return true;
		}
		
		if (args.length == 0) {
			
			
			Information info = new Information(p);
			
			info.setIssuedDate(new Date());
			info.setJoinDate(p.getFirstPlayed());
			info.setVotes(VotingPluginHooks.getInstance().getUserManager().getVotingPluginUser(p.getUniqueId()).getAllTimeTotal());
			p.getInventory().addItem(getCard(info));
			
		} else if (args.length > 0) {
			
			OfflinePlayer op = Bukkit.getOfflinePlayer(args[0]);
			
			if (!op.hasPlayedBefore()) {
				p.sendMessage("Player not found.");
				return true;
			}
			
			Information info = new Information(op);
			info.setIssuedDate(new Date());
			info.setJoinDate(op.getFirstPlayed());
			info.setVotes(VotingPluginHooks.getInstance().getUserManager().getVotingPluginUser(op.getUniqueId()).getAllTimeTotal());
			p.getInventory().addItem(getCard(info));
			
		}
		p.sendMessage("Information Card given!");
		return true;
	}
	
	public ItemStack getCard(Information info) {
		
		ItemStack card = new ItemStack(Material.NAME_TAG);
		ItemMeta meta = card.getItemMeta();
		List<String> lore = new ArrayList<String>();
		
		lore.add("");
		lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Right click to see information!");
		meta.setLore(lore);
		
		meta.setDisplayName(ChatColor.DARK_GREEN + info.getName() + "'s Information Card");
		
		NamespacedKey key = new NamespacedKey(PlayerInfoCard.getPlugin(PlayerInfoCard.class), "infocard");
		
		meta.getPersistentDataContainer().set(key, new InformationDataType(), info);
		meta.addEnchant(Enchantment.DURABILITY, 1, true);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		card.setItemMeta(meta);
		return card;
		
	}
	
}
