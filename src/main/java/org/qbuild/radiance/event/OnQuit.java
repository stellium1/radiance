package org.qbuild.radiance.event;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class OnQuit implements Listener {
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        final String playerName = p.getName();
        final TextComponent quitMessage = Component.text()
                .content("- ")
                .color(TextColor.color(0xF69CA8))
                .append(Component.text().content(playerName).color(NamedTextColor.WHITE))
                .build();
        e.quitMessage(quitMessage);
    }
}
