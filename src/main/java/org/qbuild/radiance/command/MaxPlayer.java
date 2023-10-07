package org.qbuild.radiance.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static org.qbuild.radiance.manager.FunctionManager.isOwner;

public class MaxPlayer implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("maxplayer")) {
            if (isOwner(Objects.requireNonNull(Bukkit.getPlayer(sender.getName())))) {
                try {
                    if (args.length != 0) {
                        Bukkit.broadcastMessage(String.valueOf(Bukkit.getServer().getMaxPlayers()) + " >> " + String.valueOf(args[0]));
                        Bukkit.setMaxPlayers(Integer.parseInt(args[0]));
                    } else {
                        sender.sendMessage("숫자를 입력해주세요.");
                    }
                } catch (NumberFormatException e) {
                    sender.sendMessage("숫자가 아니예여");
                }
            }
        }

        return false;
    }
}
