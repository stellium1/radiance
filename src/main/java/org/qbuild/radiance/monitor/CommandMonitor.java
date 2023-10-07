package org.qbuild.radiance.monitor;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.qbuild.radiance.Main;

public class CommandMonitor implements Listener {

    Main mainInstance = Main.getInstance();

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent e) {
        String commandContent = e.getMessage();
        if (commandContent.length() >= 35) {
            commandContent = commandContent.substring(0, 34);
            commandContent += "...";
        }
        final String senderName = e.getPlayer().getName();
        final TextComponent commandMessage = Component.text()
                .content(senderName + " ").color(TextColor.color(0xe68a8a))
                .append(Component.text().content(">> ").color(TextColor.color(0xa8929e)))
                .append(Component.text().content(commandContent).color(TextColor.color(0xF69CD3)))
                .build();

        if (!commandContent.contains("setblock") && !commandContent.contains("fill")) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (Boolean.TRUE.equals(mainInstance.commandWatchdog.get(p.getUniqueId()))) {
                    p.sendMessage(commandMessage);
                }
            }
        }
    }
}
