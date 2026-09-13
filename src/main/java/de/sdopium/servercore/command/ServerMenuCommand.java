package de.sdopium.servercore.command;

import de.sdopium.servercore.ServerCore;
import de.sdopium.servercore.util.Text;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ServerMenuCommand {
    private final ServerCore plugin;
    public ServerMenuCommand(ServerCore plugin) { this.plugin = plugin; }

    public void register() {
        plugin.getCommand("servermenu").setExecutor((sender, cmd, label, args) -> {
            if (!(sender instanceof Player p)) return true;

            Inventory inv = Bukkit.createInventory(null, 27, Text.color("&8ServerCore"));
            inv.setItem(11, item(Material.COMPASS, "&bSpawn", "&7Teleport zum Spawn"));
            inv.setItem(13, item(Material.CHEST, "&aHomes", "&7Verwalte deine Homes"));
            inv.setItem(15, item(Material.NETHER_STAR, "&dWarps", "&7Zeigt Server-Warps"));
            p.openInventory(inv);
            return true;
        });
    }

    private ItemStack item(Material material, String name, String lore) {
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(Text.color(name));
        meta.setLore(java.util.List.of(Text.color(lore)));
        stack.setItemMeta(meta);
        return stack;
    }
}
