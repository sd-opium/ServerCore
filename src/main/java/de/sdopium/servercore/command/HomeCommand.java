package de.sdopium.servercore.command;

import de.sdopium.servercore.ServerCore;
import de.sdopium.servercore.util.Text;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class HomeCommand {
    private final ServerCore plugin;
    public HomeCommand(ServerCore plugin) { this.plugin = plugin; }

    public void register() {
        CommandExecutor home = (sender, cmd, label, args) -> {
            if (!(sender instanceof Player p)) return true;
            if (args.length != 1) {
                p.sendMessage(Text.color("&eBenutzung: /home <name>"));
                return true;
            }
            Location loc = plugin.getHomeManager().getHome(p.getUniqueId(), args[0]);
            if (loc == null) {
                p.sendMessage(Text.color("&cDieses Home existiert nicht."));
                return true;
            }
            p.teleport(loc);
            p.sendMessage(Text.color("&aZum Home &f" + args[0] + " &ateleportiert."));
            return true;
        };
        plugin.getCommand("home").setExecutor(home);

        plugin.getCommand("sethome").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player p)) return true;
            if (args.length != 1) {
                p.sendMessage(Text.color("&eBenutzung: /sethome <name>"));
                return true;
            }
            plugin.getHomeManager().setHome(p.getUniqueId(), args[0], p.getLocation());
            p.sendMessage(Text.color("&aHome &f" + args[0] + " &agesetzt."));
            return true;
        });

        plugin.getCommand("delhome").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player p)) return true;
            if (args.length != 1) return true;
            plugin.getHomeManager().deleteHome(p.getUniqueId(), args[0]);
            p.sendMessage(Text.color("&aHome gelöscht."));
            return true;
        });

        plugin.getCommand("homes").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player p)) return true;
            p.sendMessage(Text.color("&bHomes: &f" + plugin.getHomeManager().getHomes(p.getUniqueId())));
            return true;
        });
    }
}
