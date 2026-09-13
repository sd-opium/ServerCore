package de.sdopium.servercore.listener;

import de.sdopium.servercore.util.Text;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncChatEvent e) {
        String name = e.getPlayer().getName();
        Component prefix = Component.text("[Server] ", NamedTextColor.AQUA);
        e.renderer((source, displayName, message, viewer) ->
                prefix.append(Component.text(name + ": ")).append(message));
    }
}
