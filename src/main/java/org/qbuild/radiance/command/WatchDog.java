package org.qbuild.radiance.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.qbuild.radiance.Main;
import org.qbuild.radiance.manager.ConfigManager;

import java.io.IOException;

public class WatchDog implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Main mainInstance = Main.getInstance();
        if (sender instanceof Player) {
            if (cmd.getName().equalsIgnoreCase("watchdog")) {
                ConfigManager cfgManager = new ConfigManager();
                Player p = (Player) sender;
                Boolean isWatching = mainInstance.commandWatchdog.get(p.getUniqueId());
                if (isWatching == null || !isWatching) {
                    mainInstance.commandWatchdog.put(p.getUniqueId(), true);
                    p.sendMessage("커맨드 보기를 켰어요.");
                } else {
                    Main.getInstance().commandWatchdog.put(p.getUniqueId(), false);
                    p.sendMessage("커맨드 보기를 껐어요.");
                }
                try {
                    cfgManager.saveConfigFile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                return true;
            }
        }
        return false;
    }
}