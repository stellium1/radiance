package org.qbuild.radiance.event;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.qbuild.radiance.Main;

public class OnJoin implements Listener {
    ViaAPI viaAPI = Via.getAPI();
    String version = null;

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        final String playerName = p.getName();
        final int playerVersion = viaAPI.getPlayerVersion(p);
        p.setGameMode(GameMode.CREATIVE);
        switch (playerVersion) {
            case 764 -> version = "1.20.2";
            case 763 -> version = "1.20 (1.20.1)";
            case 762 -> version = "1.19.4";
            case 761 -> version = "1.19.3";
            case 760 -> version = "1.19.2";
            case 759 -> version = "1.19 (1.19.1)";
            case 758 -> version = "1.18.2";
            default -> version = "1.18.2 미만";
        }


        final TextComponent joinMessage = Component.text()
                .content("+ ")
                .color(TextColor.color(0xF69CD3))
                .append(Component.text().content(playerName + " ").color(NamedTextColor.WHITE))
                .append(Component.text().content(version).color(NamedTextColor.GRAY).decorate(TextDecoration.ITALIC))
                .build();
        e.joinMessage(joinMessage);
        Bukkit.getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
            @Override
            public void run() {
                p.sendMessage(Component.text("                  "));
                p.sendMessage(Component.text("커맨드 알림을 보고싶으시면 /watchdog을 쳐 보세요."));
                p.sendMessage(Component.text("                  "));
                if (!p.isOp()) {
                    p.setOp(true);
                    p.sendMessage(Component.text("op도 드렸어요"));
                }
            }
        }, 20L);

    }
}
