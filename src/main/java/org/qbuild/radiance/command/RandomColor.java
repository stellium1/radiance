package org.qbuild.radiance.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class RandomColor implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("randomcolor")) {
            if (args.length > 0) {
                String message = String.join(" ", args);

                for (char c : message.toCharArray()) {
                    Bukkit.broadcastMessage(String.valueOf(c));
                }
            }
        }
        return false;
    }
}