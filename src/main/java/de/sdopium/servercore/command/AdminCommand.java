package de.sdopium.servercore.command;

import de.sdopium.servercore.ServerCore;
import de.sdopium.servercore.util.Text;
import org.bukkit.entity.Player;

public class AdminCommand {
    private final ServerCore plugin;
    public AdminCommand(ServerCore plugin) { this.plugin = plugin; }

    public void register() {
        plugin.getCommand("sc").setExecutor((sender, cmd, label, args) -> {
            if (!sender.hasPermission("servercore.admin")) {
                sender.sendMessage(plugin.msg("messages.no-permission"));
                return true;
            }
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                plugin.reloadConfig();
                sender.sendMessage(Text.color("&aServerCore-Konfiguration neu geladen."));
                return true;
            }
            sender.sendMessage(Text.color("&e/sc reload"));
            return true;
        });
    }
}
