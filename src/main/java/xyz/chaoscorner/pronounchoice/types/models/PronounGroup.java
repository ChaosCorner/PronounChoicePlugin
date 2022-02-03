package xyz.chaoscorner.pronounchoice.types.models;

import net.luckperms.api.model.group.Group;
import org.bukkit.ChatColor;

public record PronounGroup(String name, String description, ChatColor color, Group luckPermsGroup) {}
