package org.qbuild.radiance;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandSpy implements Listener {
/*
    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        final String commandContent = e.getMessage();
        final String senderName = e.getPlayer().getName();
        final TextComponent commandMessage = Component.text()
                .content(senderName + " ").color(TextColor.color(0xe68a8a))
                .append(Component.text().content(">> ").color(TextColor.color(0xa8929e)))
                .append(Component.text().content(commandContent).color(TextColor.color(0xF69CD3)))
                .build();
        Bukkit.broadcast(commandMessage);
    }
    */
}
