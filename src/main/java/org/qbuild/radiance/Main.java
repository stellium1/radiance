package org.qbuild.radiance;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolConfig;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;


public class Main extends JavaPlugin {
    private static Main instance = null;
    @Override
    public void onEnable() {
        instance = this;
        ViaAPI viaAPI = Via.getAPI();
        Bukkit.getPluginManager().registerEvents(new EventManager(), this);
        Bukkit.getPluginManager().registerEvents(new CommandSpy(), this);
    }

    public Main getInstance() {
        return instance;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("sendpacket")) {
            ProtocolManager manager = ProtocolLibrary.getProtocolManager();
            PacketContainer packet = manager.createPacket(PacketType.Play.Server.GAME_STATE_CHANGE);
            packet.getGameStateIDs().write(0, 5);
            packet.getFloat().write(0, 0F);

            for (Player p : Bukkit.getOnlinePlayers()) {
                manager.sendServerPacket(p, packet);
            }
            return true;
        }
        else if(command.getName().equalsIgnoreCase("maxplayer")) {
            Bukkit.getServer().setMaxPlayers(500);
        }
        return false;
    }
}
