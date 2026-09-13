package de.sdopium.servercore.manager;

import de.sdopium.servercore.ServerCore;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WarpManager {
    private final ServerCore plugin;
    private final File file;
    private final YamlConfiguration data;

    public WarpManager(ServerCore plugin) {
        this.plugin = plugin;
        file = new File(plugin.getDataFolder(), "warps.yml");
        if (!file.exists()) {
            try {
                plugin.getDataFolder().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        data = YamlConfiguration.loadConfiguration(file);
    }

    public void setWarp(String name, Location loc) {
        data.set("warps." + name.toLowerCase(), loc);
        save();
    }

    public Location getWarp(String name) {
        return data.getLocation("warps." + name.toLowerCase());
    }

    public void deleteWarp(String name) {
        data.set("warps." + name.toLowerCase(), null);
        save();
    }

    public List<String> getWarps() {
        return new ArrayList<>(data.getConfigurationSection("warps") == null
                ? List.of()
                : data.getConfigurationSection("warps").getKeys(false));
    }

    public void save() {
        try {
            data.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("warps.yml konnte nicht gespeichert werden: " + e.getMessage());
        }
    }
}
