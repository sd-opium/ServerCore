package de.sdopium.servercore.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;

public class PlayerProtectionListener implements Listener {
    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent e) {
        // Erweiterungspunkt für Welt-/Spawn-Schutz.
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        // Erweiterungspunkt für serverweite Item-Regeln.
    }
}
