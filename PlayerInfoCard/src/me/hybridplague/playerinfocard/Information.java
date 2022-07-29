package me.hybridplague.playerinfocard;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import me.clip.placeholderapi.PlaceholderAPI;

public class Information implements Serializable {

	private static final long serialVersionUID = 1L;
	private UUID uuid;
	private String name;
	
	private Date issuedDate;
	private long joinDate;
	
	private int votes;
	
	public Information(OfflinePlayer player) {
		this.setUuid(player.getUniqueId());
		this.setName(player.getName());
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public long getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(long joinDate) {
		this.joinDate = joinDate;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
	}

	public double getBalance() {
		return (Math.floor(PlayerInfoCard.getPlugin(PlayerInfoCard.class).eco.getBalance(Bukkit.getOfflinePlayer(getUuid())) * 100) / 100);
	}
	
	
	public String getPlayTime() {
		String playTime = "%playtime_time%";
		playTime = PlaceholderAPI.setPlaceholders(Bukkit.getOfflinePlayer(getUuid()), playTime);
		
		return playTime;
	}


}
