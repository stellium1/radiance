package org.qbuild.radiance.event;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.qbuild.radiance.url.SSLHelper;

import static org.bukkit.event.EventPriority.MONITOR;
import static org.qbuild.radiance.url.UrlManager.getTitle;

public class OnChat implements Listener {
    String regex = "[(http(s)?):\\/\\/(www\\.)?a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,6}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)";
    String regex2 = "^((([A-Za-z]{3,9}:(?:\\/\\/)?)(?:[-;:&=\\+\\$,\\w]+@)?[A-Za-z0-9.-]+|(?:www.|[-;:&=\\+\\$,\\w]+@)[A-Za-z0-9.-]+)((?:\\/[\\+~%\\/.\\w-_]*)?\\??(?:[-\\+=&;%@.\\w_{}]*)#?(?:[\\w]*))?)$";
    String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Whale/3.22.205.18 Safari/537.36";

    @EventHandler(priority = MONITOR)
    public void onPlayerChat(AsyncPlayerChatEvent e) {
        e.setCancelled(true);
        final Player p = e.getPlayer();
        final String pName = p.getName();
        final String pChat = e.getMessage().toString();
        String title = null;
        String url = null;
        TextComponent chatMessage = null;
        if (pChat.matches(regex) || pChat.matches(regex2)) {
            if (!pChat.contains("http")) {
                url = "https://" + pChat;
            } else {
                url = pChat;
            }
            try {
                title = getTitle(SSLHelper.getConnection(url).userAgent(userAgent).get().html());
            } catch (Exception ex) {
                title = "알 수 없음";
            }
            chatMessage = Component.text()
                    .content(pName)
                    .append(Component.text().content(": "))
                    .append(Component.text().content("[").color(TextColor.color(0xF69CD3)))
                    .append(Component.text().content(title).color(NamedTextColor.GRAY))
                    .append(Component.text().content("]").color(TextColor.color(0xF69CD3)))
                    .hoverEvent(HoverEvent.showText(Component.text(e.getMessage().toString())))
                    .clickEvent(ClickEvent.openUrl(url))
                    .build();
        } else {
            chatMessage = Component.text()
                    .content(pName)
                    .append(Component.text().content(": ").color(NamedTextColor.GRAY))
                    .append(Component.text().content(pChat).color(NamedTextColor.WHITE))
                    .build();
        }
        Bukkit.broadcast(chatMessage);
    }
}
