package de.sdopium.servercore.manager;

import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TpaManager {
    private final Map<UUID, TpaRequest> requests = new ConcurrentHashMap<>();

    public void request(Player sender, Player target, long expiresAt) {
        requests.put(target.getUniqueId(), new TpaRequest(sender.getUniqueId(), expiresAt));
    }

    public TpaRequest get(Player target) {
        TpaRequest request = requests.get(target.getUniqueId());
        if (request == null) return null;
        if (request.expiresAt() < System.currentTimeMillis()) {
            requests.remove(target.getUniqueId());
            return null;
        }
        return request;
    }

    public void remove(Player target) {
        requests.remove(target.getUniqueId());
    }

    public record TpaRequest(UUID sender, long expiresAt) {}
}
