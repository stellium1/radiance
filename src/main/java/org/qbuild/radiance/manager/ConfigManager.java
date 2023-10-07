package org.qbuild.radiance.manager;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.qbuild.radiance.Main;


import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;

public class ConfigManager {
    Main mainInstance = Main.getInstance();
    YamlConfiguration cfg = new YamlConfiguration();
    File configFile = mainInstance.configFile;

    // variables.yml 없으면 > 생성
    public void initConfigFile() throws IOException {
        if (!configFile.exists()) {
            configFile.createNewFile();
            mainInstance.getLogger().log(Level.INFO, "variables.yml을 만들게요.");
            }
    }

    //variables.yml을 읽어서 HashMap에 저장
    public void configToHashMap() throws IOException, InvalidConfigurationException {
        cfg.load(configFile);
        ConfigurationSection playerSection = cfg.getConfigurationSection("players");
            if (playerSection != null) {
                for (String playerKey : playerSection.getKeys(false)) {
                    UUID playerUUID = UUID.fromString(playerKey);
                    boolean doWatchdog = playerSection.getBoolean(playerKey + ".doWatchdog");
                    mainInstance.commandWatchdog.put(playerUUID, doWatchdog);
                }
            }
        }


    // 커맨드 감시하기 여부 (UUID, boolean) 저장
    public void saveConfigFile() throws IOException {
        for (Map.Entry<UUID, Boolean> entry : Main.getInstance().commandWatchdog.entrySet()) {
            UUID key = entry.getKey();
            boolean value = entry.getValue();
            cfg.set("players." + key + ".doWatchdog", value);
        }
        cfg.save(configFile);
    }


}
