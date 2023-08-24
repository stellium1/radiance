package org.qbuild.radiance;

import com.destroystokyo.paper.network.NetworkClient;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.event.player.PlayerQuitEvent;


public class EventManager implements Listener {

    ViaAPI viaAPI = Via.getAPI();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        String version = null;
        final String playerName = e.getPlayer().getName();
        final int playerVersion = viaAPI.getPlayerVersion(p);
        switch (playerVersion) {
            case 763: version = "1.20 (1.20.1)"; break;
            case 762: version = "1.19.4"; break;
            case 761: version = "1.19.3"; break;
            case 760: version = "1.19.2"; break;
            case 759: version = "1.19 (1.19.1)" ; break;
        }

        final TextComponent joinMessage = Component.text()
                .content("+ ")
                .color(TextColor.color(0xF69CD3))
                .append(Component.text().content(playerName + " ").color(NamedTextColor.WHITE))
                .append(Component.text().content(version).color(NamedTextColor.GRAY).decorate(TextDecoration.ITALIC))
                .build();
        e.joinMessage(joinMessage);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        final String playerName = e.getPlayer().getName();
        final TextComponent quitMessage = Component.text()
                .content("- ")
                .color(TextColor.color(0xF69CA8))
                .append(Component.text().content(playerName).color(NamedTextColor.WHITE))
                .build();
        e.quitMessage(quitMessage);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        final String playerName = e.getPlayer().getName();
        final String playerChat = e.getMessage().toString();
        final TextComponent chatMessage = Component.text()
                .content(playerName)
                .append(Component.text().content(": ").color(NamedTextColor.GRAY))
                .append(Component.text().content(playerChat).color(NamedTextColor.WHITE))
                .build();
        Bukkit.broadcast(chatMessage);
    }

}
