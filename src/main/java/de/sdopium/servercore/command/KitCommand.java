package de.sdopium.servercore.command;

import de.sdopium.servercore.ServerCore;
import de.sdopium.servercore.util.Text;
import org.bukkit.entity.Player;

public class KitCommand {
    private final ServerCore plugin;
    public KitCommand(ServerCore plugin) { this.plugin = plugin; }

    public void register() {
        plugin.getCommand("kits").setExecutor((sender, cmd, label, args) -> {
            sender.sendMessage(Text.color("&bVerfügbares Kit: &fstarter"));
            return true;
        });

        plugin.getCommand("kit").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player p)) return true;
            if (args.length != 1) {
                p.sendMessage(Text.color("&eBenutzung: /kit <name>"));
                return true;
            }
            String kit = args[0].toLowerCase();
            if (!plugin.getKitManager().hasKit(kit)) {
                p.sendMessage(Text.color("&cDieses Kit existiert nicht."));
                return true;
            }
            if (!plugin.getKitManager().canUse(p, kit)) {
                p.sendMessage(Text.color("&cKit ist noch auf Cooldown: &f" +
                        plugin.getKitManager().remaining(p, kit) + "s"));
                return true;
            }
            plugin.getKitManager().give(p, kit);
            p.sendMessage(Text.color("&aKit &f" + kit + " &aerhalten."));
            return true;
        });
    }
}
