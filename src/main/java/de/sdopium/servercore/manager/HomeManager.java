package de.sdopium.servercore.manager;

import de.sdopium.servercore.ServerCore;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HomeManager {
    private final ServerCore plugin;
    private final File file;
    private final YamlConfiguration data;

    public HomeManager(ServerCore plugin) {
        this.plugin = plugin;
        file = new File(plugin.getDataFolder(), "homes.yml");
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

    public void setHome(UUID uuid, String name, Location location) {
        data.set(uuid + "." + name.toLowerCase(), location);
        save();
    }

    public Location getHome(UUID uuid, String name) {
        return data.getLocation(uuid + "." + name.toLowerCase());
    }

    public void deleteHome(UUID uuid, String name) {
        data.set(uuid + "." + name.toLowerCase(), null);
        save();
    }

    public List<String> getHomes(UUID uuid) {
        return new ArrayList<>(data.getStringList(uuid + ".list"));
    }

    public void save() {
        try {
            data.save(file);
        } catch (IOException e) {
            plugin.getLogger().warning("homes.yml konnte nicht gespeichert werden: " + e.getMessage());
        }
    }
}
