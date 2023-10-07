package org.qbuild.radiance.command;

import java.io.IOException;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.nodes.Document;
import org.qbuild.radiance.Main;
import org.qbuild.radiance.url.SSLHelper;

public class NameMc implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull final String[] args) {
        if (cmd.getName().equalsIgnoreCase("namemc")) {
            final String url = "http://35.75.1.79/profile/" + args[0];
            new BukkitRunnable() {
                @Override
                public void run() {
                    try {
                        Document document = SSLHelper.getConnection(url).ignoreContentType(true).get();
                        JSONParser parser = new JSONParser();
                        JSONObject obj = (JSONObject) parser.parse(document.text());
                        JSONArray historyArray = (JSONArray) obj.get("name_history");
                        String cSender = sender.getName();
                        if (cSender.equals(args[0])) {
                            cSender = "셀고";
                        }
                        int cnt = 0;
                        Bukkit.broadcastMessage(args[0] + "의 닉변 기록 " + ChatColor.GRAY + ChatColor.ITALIC + "(" + cSender + ")");

                        for (Object historyName : historyArray) {
                            cnt++;
                            JSONObject nameHistoryObject = (JSONObject) historyName;
                            String name = (String) nameHistoryObject.get("name");

                            if (name.length() < 2) {
                                continue;
                            }

                            TextComponent message = Component.text()
                                    .color(TextColor.color(0xF69CD3))
                                    .append(Component.text("" + (historyArray.size() + 1 - cnt) + ". "))
                                    .append(Component.text(name).color(NamedTextColor.WHITE))
                                    .build();

                            Bukkit.broadcast(message);
                        }
                    } catch (IOException | ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            }.runTaskAsynchronously(Main.getInstance());
            return true;
        }
        return false;
    }
}
