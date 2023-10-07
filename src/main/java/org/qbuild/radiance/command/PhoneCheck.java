package org.qbuild.radiance.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PhoneCheck implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("phonecheck")) {
            for (Player lp : Bukkit.getOnlinePlayers()) {
                String language = lp.locale().toString();
                int distance = lp.getClientViewDistance();
                if ((language.equals("en_GB") && distance == 2) || lp.getClientBrandName() == null) {
                    Bukkit.broadcastMessage(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + lp.getName() + ChatColor.RESET + "(이)가 폰접같아요");
                    //  lp.kickPlayer("폰접 ㅂㅂ");
                }
            }
            return true;
        }
        return false;
    }
}
