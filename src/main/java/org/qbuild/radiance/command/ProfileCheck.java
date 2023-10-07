package org.qbuild.radiance.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ProfileCheck implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (cmd.getName().equals("profilecheck")) {
            Player p = Bukkit.getPlayer(args[0]);
            Bukkit.broadcastMessage("======" + p.getName() + "======");
            Bukkit.broadcastMessage("시야거리 : " + p.getClientViewDistance());
            Bukkit.broadcastMessage("언어 : " + p.getLocale().toString());
            Bukkit.broadcastMessage("브랜드 : " + p.getClientBrandName());
            Bukkit.broadcastMessage("UUID: " + p.getUniqueId());
            return true;
        }
        return false;
    }
}