package me.hybridplague.playerinfocard;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;


import net.milkbowl.vault.economy.Economy;

public class PlayerInfoCard extends JavaPlugin {

	@Override
	public void onEnable() {
		if (!setupEconomy()) {
			System.out.println(ChatColor.RED + "You must have Vault installed.");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new UseCard(), this);
		getCommand("getCard").setExecutor(new GetCard());
	}
	
	@Override
	public void onDisable() {
	}
	
	
	public Economy eco;
	
	private boolean setupEconomy() {
		RegisteredServiceProvider<Economy> economy = PlayerInfoCard.getPlugin(PlayerInfoCard.class).getServer().
				getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economy != null)
			eco = economy.getProvider();
		return (eco != null);
	}
}
