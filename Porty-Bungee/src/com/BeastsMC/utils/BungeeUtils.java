package com.BeastsMC.utils;

import java.util.Collection;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeUtils {
	public static ProxiedPlayer matchPlayer(String partialName) {
		partialName = partialName.toLowerCase();
		Collection<ProxiedPlayer> online = ProxyServer.getInstance().getPlayers();
		for(ProxiedPlayer player : online) {
			String name = player.getName().toLowerCase();
			if(name.contains(partialName)) {
				return player;
			}
		}
		return null;
	}
}
