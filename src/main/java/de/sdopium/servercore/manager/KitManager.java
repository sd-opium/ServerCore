package de.sdopium.servercore.manager;

import de.sdopium.servercore.ServerCore;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class KitManager {
    private final ServerCore plugin;
    private final YamlConfiguration kits;
    private final Map<String, Map<String, Long>> cooldowns = new HashMap<>();

    public KitManager(ServerCore plugin) {
        this.plugin = plugin;
        File file = new File(plugin.getDataFolder(), "kits.yml");
        kits = YamlConfiguration.loadConfiguration(file);
    }

    public boolean hasKit(String name) {
        return kits.contains(name);
    }

    public boolean canUse(Player player, String name) {
        String permission = kits.getString(name + ".permission", "");
        if (!permission.isBlank() && !player.hasPermission(permission)) return false;
        long cooldown = kits.getLong(name + ".cooldown-seconds", 0);
        long until = cooldowns.getOrDefault(name, Map.of()).getOrDefault(player.getName(), 0L);
        return until <= System.currentTimeMillis();
    }

    public long remaining(Player player, String name) {
        long until = cooldowns.getOrDefault(name, Map.of()).getOrDefault(player.getName(), 0L);
        return Math.max(0, (until - System.currentTimeMillis()) / 1000);
    }

    public void give(Player player, String name) {
        for (String raw : kits.getStringList(name + ".items")) {
            String[] parts = raw.split(":");
            Material material = Material.matchMaterial(parts[0]);
            int amount = parts.length > 1 ? Integer.parseInt(parts[1]) : 1;
            if (material != null) player.getInventory().addItem(new ItemStack(material, amount));
        }
        long cooldown = kits.getLong(name + ".cooldown-seconds", 0);
        cooldowns.computeIfAbsent(name, k -> new HashMap<>())
                .put(player.getName(), System.currentTimeMillis() + cooldown * 1000L);
    }
}
