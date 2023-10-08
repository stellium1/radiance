package org.qbuild.radiance.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.qbuild.radiance.manager.FunctionManager;

public class ClearChat implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (FunctionManager.isOwner(Bukkit.getPlayer(sender.getName()))) {
            for (int i = 0; i < 50; i++) {
                Bukkit.broadcastMessage("");
            }
        }
        return false;
    }
}
