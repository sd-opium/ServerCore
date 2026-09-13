package de.sdopium.servercore.command;

import de.sdopium.servercore.ServerCore;
import de.sdopium.servercore.manager.TpaManager;
import de.sdopium.servercore.util.Text;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TpaCommand {
    private final ServerCore plugin;
    public TpaCommand(ServerCore plugin) { this.plugin = plugin; }

    public void register() {
        plugin.getCommand("tpa").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player p)) return true;
            if (args.length != 1) return true;
            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                p.sendMessage(Text.color("&cSpieler nicht gefunden."));
                return true;
            }
            if (target.equals(p)) {
                p.sendMessage(Text.color("&cDu kannst dir nicht selbst eine Anfrage schicken."));
                return true;
            }
            long timeout = plugin.getConfig().getLong("settings.tpa-timeout-seconds", 30) * 1000L;
            plugin.getTpaManager().request(p, target, System.currentTimeMillis() + timeout);
            p.sendMessage(Text.color("&aTPA-Anfrage an &f" + target.getName() + " &agesendet."));
            target.sendMessage(Text.color("&e" + p.getName() + " &7möchte sich zu dir teleportieren."));
            target.sendMessage(Text.color("&a/tpaccept &7oder &c/tpdeny"));
            return true;
        });

        plugin.getCommand("tpaccept").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player target)) return true;
            TpaManager.TpaRequest request = plugin.getTpaManager().get(target);
            if (request == null) {
                target.sendMessage(Text.color("&cKeine gültige TPA-Anfrage."));
                return true;
            }
            Player senderPlayer = Bukkit.getPlayer(request.sender());
            plugin.getTpaManager().remove(target);
            if (senderPlayer != null) {
                senderPlayer.teleport(target.getLocation());
                senderPlayer.sendMessage(Text.color("&aTPA angenommen."));
                target.sendMessage(Text.color("&aTPA angenommen."));
            }
            return true;
        });

        plugin.getCommand("tpdeny").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player p)) return true;
            plugin.getTpaManager().remove(p);
            p.sendMessage(Text.color("&7TPA-Anfrage abgelehnt."));
            return true;
        });
    }
}
