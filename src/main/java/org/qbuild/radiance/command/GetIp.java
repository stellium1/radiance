package org.qbuild.radiance.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import static org.qbuild.radiance.manager.FunctionManager.isOwner;

public class GetIp implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("getip")) {
            if (args.length > 0 && isOwner((Player) sender)) {
                Player target = Bukkit.getPlayer(args[0]);
                assert target != null;
                sender.sendMessage(target.getAddress().toString());
            }
        }
        return false;
    }
}
