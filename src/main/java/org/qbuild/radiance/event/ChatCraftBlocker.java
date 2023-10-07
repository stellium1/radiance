package org.qbuild.radiance.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class ChatCraftBlocker implements Listener {
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        Player p = e.getPlayer();
        int distance = p.getClientViewDistance();
        String language = p.getLocale().toString();

        if ((distance == 2 && language.equals("en_GB")) || p.getClientBrandName() == null ) {
           // e.setCancelled(true);
           // e.getPlayer().sendMessage("우왓..");
        }
    }
}
