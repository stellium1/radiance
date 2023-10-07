package org.qbuild.radiance.monitor;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.qbuild.radiance.Main;

public class CommandBlockMonitor implements Listener {
    Main plugin;

    public CommandBlockMonitor(Main plugin) {
        this.plugin = plugin;
    }

    public static void packetListening() {
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(Main.getInstance(), ListenerPriority.HIGH, PacketType.Play.Client.SET_COMMAND_BLOCK) {

            @Override
            public void onPacketReceiving(PacketEvent e) {
                String command = null;
                Player p = e.getPlayer();
                PacketContainer packet = e.getPacket();
                if (!packet.getStrings().read(0).isEmpty()) {
                    if (packet.getStrings().read(0).length() > 23) {
                        command = packet.getStrings().read(0).substring(0, 22) + "..";
                    } else {
                        command = packet.getStrings().read(0).toString();
                    }
                    final TextComponent commandMessage = Component.text()
                            .content(p.getName() + "(✡) ").color(TextColor.color(0xe68a8a))
                            .append(Component.text().content(">> ").color(TextColor.color(0xa8929e)))
                            .append(Component.text().content(command).color(TextColor.color(0xF69CD3)))
                            .build();
                    Bukkit.broadcast(commandMessage);
                }
            }
            });

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(Main.getInstance(), ListenerPriority.HIGH, PacketType.Play.Client.SET_COMMAND_MINECART) {
            @Override
            public void onPacketReceiving(PacketEvent e) {
                Player p = e.getPlayer();
                PacketContainer packet = e.getPacket();
                for (Player pl : Bukkit.getOnlinePlayers()) {
                    pl.sendMessage(p.getName() + " >> " + packet.getStrings().read(0));
                }
            }
        });
    }
}