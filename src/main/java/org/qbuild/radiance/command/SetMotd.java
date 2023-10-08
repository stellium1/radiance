package org.qbuild.radiance.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.qbuild.radiance.manager.FunctionManager;

import java.util.Objects;

public class SetMotd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("setmotd") && FunctionManager.isOwner(Objects.requireNonNull(Bukkit.getPlayer(sender.getName())))) {
            if (!(args[0].isEmpty())) {
                String motd = String.join(" ", args);
                Bukkit.broadcastMessage(Bukkit.getServer().getMotd() + " >> " + motd);
                Bukkit.getServer().setMotd(motd);
            }
        }
        return false;
    }
}