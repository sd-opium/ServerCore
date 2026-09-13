package de.sdopium.servercore.listener;

import de.sdopium.servercore.ServerCore;
import de.sdopium.servercore.util.Text;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class MenuListener implements Listener {
    private final ServerCore plugin;
    public MenuListener(ServerCore plugin) { this.plugin = plugin; }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (!Text.color("&8ServerCore").equals(e.getView().getTitle())) return;
        e.setCancelled(true);

        if (!(e.getWhoClicked() instanceof org.bukkit.entity.Player p)) return;
        ItemStack clicked = e.getCurrentItem();
        if (clicked == null || !clicked.hasItemMeta()) return;

        String name = clicked.getItemMeta().getDisplayName();
        if (name.equals(Text.color("&bSpawn"))) {
            p.performCommand("spawn");
            p.closeInventory();
        }
        if (name.equals(Text.color("&aHomes"))) {
            p.performCommand("homes");
        }
        if (name.equals(Text.color("&dWarps"))) {
            p.performCommand("warps");
        }
    }
}
