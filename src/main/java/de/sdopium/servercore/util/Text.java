package de.sdopium.servercore.util;

import org.bukkit.ChatColor;

public final class Text {
    private Text() {}
    public static String color(String input) {
        return ChatColor.translateAlternateColorCodes('&', input == null ? "" : input);
    }
}
