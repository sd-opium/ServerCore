package de.sdopium.servercore;

import de.sdopium.servercore.command.AdminCommand;
import de.sdopium.servercore.command.HomeCommand;
import de.sdopium.servercore.command.KitCommand;
import de.sdopium.servercore.command.ServerMenuCommand;
import de.sdopium.servercore.command.SpawnCommand;
import de.sdopium.servercore.command.TpaCommand;
import de.sdopium.servercore.command.WarpCommand;
import de.sdopium.servercore.listener.ChatListener;
import de.sdopium.servercore.listener.JoinQuitListener;
import de.sdopium.servercore.listener.MenuListener;
import de.sdopium.servercore.listener.PlayerProtectionListener;
import de.sdopium.servercore.manager.HomeManager;
import de.sdopium.servercore.manager.KitManager;
import de.sdopium.servercore.manager.TpaManager;
import de.sdopium.servercore.manager.WarpManager;

public final class ServerCore extends org.bukkit.plugin.java.JavaPlugin {

    private HomeManager homeManager;
    private WarpManager warpManager;
    private TpaManager tpaManager;
    private KitManager kitManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        saveResource("kits.yml", false);
        saveResource("menus.yml", false);

        homeManager = new HomeManager(this);
        warpManager = new WarpManager(this);
        tpaManager = new TpaManager(this);
        kitManager = new KitManager(this);

        getServer().getPluginManager().registerEvents(new JoinQuitListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        getServer().getPluginManager().registerEvents(new MenuListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerProtectionListener(this), this);

        new SpawnCommand(this).register();
        new HomeCommand(this).register();
        new WarpCommand(this).register();
        new TpaCommand(this).register();
        new KitCommand(this).register();
        new ServerMenuCommand(this).register();
        new AdminCommand(this).register();

        getLogger().info("ServerCore wurde erfolgreich aktiviert.");
    }

    @Override
    public void onDisable() {
        if (homeManager != null) homeManager.save();
        if (warpManager != null) warpManager.save();
        getLogger().info("ServerCore wurde beendet.");
    }

    public HomeManager getHomeManager() { return homeManager; }
    public WarpManager getWarpManager() { return warpManager; }
    public TpaManager getTpaManager() { return tpaManager; }
    public KitManager getKitManager() { return kitManager; }

    public String msg(String path) {
        String prefix = getConfig().getString("messages.prefix", "");
        String text = getConfig().getString(path, path);
        return Text.color(prefix + text);
    }
}
