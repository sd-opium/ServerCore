package de.sdopium.servercore.command;

import de.sdopium.servercore.ServerCore;
import de.sdopium.servercore.util.Text;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class WarpCommand {
    private final ServerCore plugin;
    public WarpCommand(ServerCore plugin) { this.plugin = plugin; }

    public void register() {
        plugin.getCommand("warp").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player p)) return true;
            if (args.length != 1) {
                p.sendMessage(Text.color("&eBenutzung: /warp <name>"));
                return true;
            }
            Location loc = plugin.getWarpManager().getWarp(args[0]);
            if (loc == null) {
                p.sendMessage(Text.color("&cDieser Warp existiert nicht."));
                return true;
            }
            p.teleport(loc);
            p.sendMessage(Text.color("&aZum Warp &f" + args[0] + " &ateleportiert."));
            return true;
        });

        plugin.getCommand("setwarp").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player p)) return true;
            if (args.length != 1) return true;
            plugin.getWarpManager().setWarp(args[0], p.getLocation());
            p.sendMessage(Text.color("&aWarp gesetzt."));
            return true;
        });

        plugin.getCommand("delwarp").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player p)) return true;
            if (args.length != 1) return true;
            plugin.getWarpManager().deleteWarp(args[0]);
            p.sendMessage(Text.color("&aWarp gelöscht."));
            return true;
        });

        plugin.getCommand("warps").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player p)) return true;
            p.sendMessage(Text.color("&bWarps: &f" + plugin.getWarpManager().getWarps()));
            return true;
        });
    }
}
