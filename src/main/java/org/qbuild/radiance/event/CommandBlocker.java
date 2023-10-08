package org.qbuild.radiance.event;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.qbuild.radiance.manager.FunctionManager;

import java.util.regex.Pattern;

import static org.qbuild.radiance.manager.FunctionManager.isOwner;

public class CommandBlocker implements Listener {
    String regex = ".*\\b(stopsound|playsound|stop|reload|restart|plugman)\\b.*";

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onCommand(PlayerCommandPreprocessEvent e) {
        String cmd = e.getMessage();
        if (Pattern.matches(regex, cmd) && !isOwner(e.getPlayer())) {
            e.setCancelled(true);
            //   Bukkit.broadcastMessage(String.valueOf(isOwner(e.getPlayer())));
        }
    }
}
