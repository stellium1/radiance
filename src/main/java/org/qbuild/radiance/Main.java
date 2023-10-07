package org.qbuild.radiance;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.plugin.java.JavaPlugin;
import org.qbuild.radiance.command.*;
import org.qbuild.radiance.event.*;
import org.qbuild.radiance.manager.ConfigManager;
import org.qbuild.radiance.monitor.CommandBlockMonitor;
import org.qbuild.radiance.monitor.CommandMonitor;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public class Main extends JavaPlugin {
    private static Main instance = null;
    public Map<UUID, Boolean> commandWatchdog = Collections.synchronizedMap(new HashMap<>());
    private static ProtocolManager ptlManager = ProtocolLibrary.getProtocolManager();
    public File configFile = new File("plugins/radiance/variables.yml");

    @Override
    public void onEnable() {
        instance = this;
        CommandBlockMonitor.packetListening();

        setGameRule();
        registeringCommand();
        registeringEvent();

        ConfigManager cfgManager = new ConfigManager();

        try {
            cfgManager.initConfigFile();
            cfgManager.configToHashMap();
        } catch (IOException | InvalidConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDisable() {
        ConfigManager cfgManager = new ConfigManager();
        try {
            cfgManager.saveConfigFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Main getInstance() {
        return instance;
    }

    public static ProtocolManager getProtocolManger() {
        return ptlManager;
    }

    public void registeringCommand() {
        this.getCommand("maxplayer").setExecutor(new MaxPlayer());
        this.getCommand("phonecheck").setExecutor(new PhoneCheck());
        this.getCommand("profilecheck").setExecutor(new ProfileCheck());
        this.getCommand("watchdog").setExecutor(new WatchDog());
        this.getCommand("namemc").setExecutor(new NameMc());
        this.getCommand("getip").setExecutor(new GetIp());
        this.getCommand("serverstatus").setExecutor(new ServerStatus());

    }

    public void registeringEvent() {
        Bukkit.getPluginManager().registerEvents(new OnChat(), this);
        Bukkit.getPluginManager().registerEvents(new OnJoin(), this);
        Bukkit.getPluginManager().registerEvents(new OnQuit(), this);
        Bukkit.getPluginManager().registerEvents(new CommandMonitor(), this);
        Bukkit.getPluginManager().registerEvents(new ChatCraftBlocker(), this);
        Bukkit.getPluginManager().registerEvents(new CommandBlocker(), this);
    }

    public void setGameRule() {
        Bukkit.getServer().getWorld("world").setGameRule(GameRule.SEND_COMMAND_FEEDBACK, false);
        Bukkit.getServer().getWorld("world").setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
    }

}

