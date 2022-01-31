package xyz.chaoscorner.pronounchoice.util.types;

import org.bukkit.ChatColor;

public class Pronoun {
    private String name;
    private String description;
    private ChatColor color;

    public Pronoun(String name, String description, ChatColor color) {
        this.name = name;
        this.description = description;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ChatColor getColor() {
        return color;
    }
}
