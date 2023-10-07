package org.qbuild.radiance.command;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
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

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ServerStatus implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if (cmd.getName().equalsIgnoreCase("serverstatus")) {
            if (args.length > 0) {
                String serverIp = URLEncoder.encode(args[0], StandardCharsets.UTF_8);
                String url = "https://api.mcsrvstat.us/3/" + serverIp;
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        {
                            try {
                                Document document = SSLHelper.getConnection(url).ignoreContentType(true).get();
                                JSONParser jsonParser = new JSONParser();
                                Object obj = jsonParser.parse(document.text());
                                JSONObject jsonObj = (JSONObject) obj;
                                JSONObject debugObj = (JSONObject) jsonObj.get("debug");
                                boolean ping = (boolean) debugObj.get("ping");
                                if (!ping) {
                                    Bukkit.broadcastMessage(args[0] + " 서버가 꺼져있어요.");
                                }
                                JSONObject motdObj = (JSONObject) jsonObj.get("motd");
                                JSONArray cleanArray = (JSONArray) motdObj.get("raw");
                                JSONObject list = (JSONObject) jsonObj.get("list");


                                System.out.println(list);
                                Bukkit.broadcastMessage(ChatColor.of("#FFC834") + args[0] + ChatColor.of("#FFD768") + " 서버를 확인합니다" + ChatColor.GRAY + ChatColor.ITALIC + " (" + sender.getName() + ")");
                                Bukkit.broadcastMessage(ChatColor.of("#FFC834") + "서버 아이피" + ChatColor.GRAY + ": " + ChatColor.of("#FFD768") + jsonObj.get("ip").toString());
                                Bukkit.broadcastMessage(ChatColor.of("#FFC834") + "MOTD" + ChatColor.GRAY + ": " + cleanArray.get(0));
                                Bukkit.broadcastMessage("");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }.runTaskAsynchronously(Main.getInstance());
            }
        }
        return false;
    }
}