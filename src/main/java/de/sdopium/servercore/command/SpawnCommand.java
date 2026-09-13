package de.sdopium.servercore.command;

import de.sdopium.servercore.ServerCore;
import de.sdopium.servercore.util.Text;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class SpawnCommand {
    private final ServerCore plugin;
    public SpawnCommand(ServerCore plugin) { this.plugin = plugin; }

    public void register() {
        CommandExecutor executor = (sender, cmd, label, args) -> {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(plugin.msg("messages.player-only"));
                return true;
            }
            Location spawn = plugin.getConfig().getLocation("spawn");
            if (spawn == null) {
                player.sendMessage(Text.color("&cEs wurde noch kein Spawn gesetzt."));
                return true;
            }
            player.teleport(spawn);
            player.sendMessage(Text.color("&aDu wurdest zum Spawn teleportiert."));
            return true;
        };
        plugin.getCommand("spawn").setExecutor(executor);
        plugin.getCommand("setspawn").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player player)) return true;
            plugin.getConfig().set("spawn", player.getLocation());
            plugin.saveConfig();
            player.sendMessage(Text.color("&aSpawn gesetzt."));
            return true;
        });
    }
}
