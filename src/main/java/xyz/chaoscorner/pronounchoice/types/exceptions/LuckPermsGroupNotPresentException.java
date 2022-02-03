package xyz.chaoscorner.pronounchoice.types.exceptions;

import org.bukkit.entity.Player;
import xyz.chaoscorner.pronounchoice.types.models.PronounGroup;

public class LuckPermsGroupNotPresentException extends Exception {
    public LuckPermsGroupNotPresentException(PronounGroup pronounGroup) {
        super(String.format("LuckPerms group is not included in the given PronounGroup: %s", pronounGroup.name()));
    }

    public LuckPermsGroupNotPresentException(PronounGroup pronounGroup, Player player) {
        super(String.format("Player (name: %s | uuid: %s) cannot be added to PronounGroup. LuckPerms group is not included in the given PronounGroup: %s", player.getName(), player.getUniqueId(), pronounGroup.name()));
    }
}
